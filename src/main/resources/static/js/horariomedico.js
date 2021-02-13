//Configuracion de timepicker y date
$("[data-mask]").inputmask();
$(".timepicker").timepicker({ showInputs: false, showMeridian: false, minuteStep: 30 });

var tabla;

function initDataTable() {  //inicializar datatable
    tabla = $("#tbl_horarios").DataTable({
        "aaSorting": [[0, 'desc']],
        "bSort": true,
        "aoColumns": [
            { "bSortable": false },//no ordenable
            { "bSortable": false },
            { "bSortable": false },
            null,
            null,
        ]
    });

    tabla.fnSetColumnVis(2, false); //funcion ocultar columna de id
 };
 initDataTable();


$("#btnBuscar").on("click", function (event) {

    event.preventDefault();

    // obtener los datos del texto de dni
    var dni = $("#dni").val();

    //var obj = JSON.stringify({ dni: dni });
    var obj= {}

    if (dni.length > 0) {
        // llamada a ajax
        $.ajax({
            type: "POST",
            url: "/medicos/findmedicoby/"+dni,
            data: obj,
            contentType: 'application/json',
            dataType: 'json',
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr.status + " \n" + xhr.responseText, "\n" + thrownError);
            },
            success: function (data) {
                console.log("éxito", data);
                if (data !== null) {
                    llenarDatosMedico(data);
                    listHorarios(data.id);//pasar idmedico
                } else {
                     llenarDatosMedicoDefault(data);
                }
            }
        });
    } else {
        console.log("No ha ingresado un dni.");
    }
});

function llenarDatosMedico(obj) {
    $("#nombres").text(obj.nombre);
    $("#apellidos").text(obj.apellido);
    $("#especialidad").text(obj.especialidad);
    $("#txtMedico").val(obj.id);
}

function llenarDatosMedicoDefault() {
    alert("No existe médico con documento " + $("#dni").val());
    $("#nombres").text("");
    $("#apellidos").text("");
    $("#especialidad").text("");
    $("#txtMedico").val("0");
    $("#dni").val("");
}

// agregar un horario
$("#btnAgregar").on("click", function (event) {
    event.preventDefault();
    //obtener los valores de los campos
    var fecha, hora, idmedico;
    fecha = $("#txtFecha").val();
    hora = $("#txtHoraInicio").val();
    idmedico = $("#txtMedico").val();

    //console.log(fecha, hora)
    if (fecha.length > 0 && hora.length > 0 && idmedico > 0) {
         var obj = JSON.stringify({ fecha: fecha, hora: hora, idmedico: idmedico });
         // llamada a ajax
         $.ajax({
             type: "POST",
             url: "HorarioAtencion.html/AgregarHorario",
             data: obj,
             contentType: 'application/json; charset=utf-8',
             error: function (xhr, ajaxOptions, thrownError) {
                 console.log(xhr.status + " \n" + xhr.responseText, "\n" + thrownError);
             },
             success: function (data) {
                console.log("éxito", data.d);
                // cerrar ventana modal usando jquery
                $("#AgregarHorario").modal('toggle');
                  addRow(data.d);

             }
         });

     } else {
         console.log("Ingrese los datos requeridos")
    //     if (parseInt(idmedico) < 1) {
    //         alert("Ingrese la información del médico.");
    //     } else {
    //         alert("Ingrese los datos requeridos.");
    //     }
     }
});

function addRow(obj) { //agregar fila

    var fecha = moment(obj.Fecha).toDate();//mandas fecha libreria moment

    tabla.fnAddData( //recibe array
        ['<button type="button" value="Actualizar" title="Actualizar" class="btn btn-primary btn-edit" data-target="#imodal" data-toggle="modal"><i class="fa fa-check-square-o" aria-hidden="true"></i></button>&nbsp;',
          '<button type="button" value="Eliminar" title="Eliminar" class="btn btn-danger btn-delete"><i class="fa fa-minus-square-o" aria-hidden="true"></i></button>',
          obj.IdHorarioAtencion,//mandas id
          fecha.format("dd/MM/yyyy"),
          obj.Hora.hora//mandas hora
        ]
    );
}


 
 function listHorarios(idmedico) { 

    var obj = JSON.stringify({ idmedico: idmedico });

    $.ajax({
        type: "POST",
        url: "HorarioAtencion/ListarHorariosAtencion", //url/metodo
        data: obj,
        contentType: 'application/json; charset=utf-8',
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status + " \n" + xhr.responseText, "\n" + thrownError);
        },
        success: function (data) {
            //console.log("éxito", data);
            tabla.fnClearTable();
            for (var i = 0; i < data.d.length; i++) {
                addRow(data.d[i]);
            }
        }
    });

}

// evento click para boton eliminar
$(document).on('click', '.btn-delete', function (e) {
    e.preventDefault();
    //primer metodo: eliminar fila del datatable
    var row = $(this).parent().parent()[0];
    var dataRow = tabla.fnGetData(row);

    var response = confirm("¿Está seguro que desea eliminar el horario?");
    if (response) {
        //segundo metodo:enviar el codigo del paciente al servidor y eliminarlo, renderizar el datatable
        //paso 1:enviar el id por medio de ajax
        deleteDataAjax(dataRow[2]); //eliminar desde id
        //paso 2:trae horarios nuevamente
        listHorarios($("#txtMedico").val());
    }

});

function deleteDataAjax(data) { //se envia data

    var obj = JSON.stringify({ id: JSON.stringify(data) });

    $.ajax({
        type: "POST",
        url: "HorarioAtencion.html/EliminarHorarioAtencion",
        data: obj,
        dataType: "json",
        contentType: 'application/json; charset=utf-8',
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status + " \n" + xhr.responseText, "\n" + thrownError);
        },
        success: function (response) {
            if (response.d) {
                alert("Registro eliminado de manera correcta.");
            } else {
                alert("No se pudo eliminar el registro.");
            }
        }
    });
}

// evento click para boton editar
$(document).on('click', '.btn-edit', function (e) {
    e.preventDefault();

    var row = $(this).parent().parent()[0];
    var dataRow = tabla.fnGetData(row);
    /*
    idmedico
    idhorarioatencion
    fecha 
    hora
    */
    llenarDatosHorario(dataRow);
});

function llenarDatosHorario(data) {
    $("#txtEditarFecha").val(data[3]);
    $("#txtEditarHora").val(data[4]);
    $("#txtIdHorario").val(data[2]);
}

$("#btnEditar").click(function (e) {
    e.preventDefault();

    var obj = JSON.stringify({
        idmedico: $("#txtMedico").val(),
        idhorario: $("#txtIdHorario").val(),
        fecha: $("#txtEditarFecha").val(),
        hora: $("#txtEditarHora").val()
    });

    $.ajax({
        type: "POST",
        url: "HorarioAtencion.html/ActualizarHorarioAtencion",
        data: obj,
        dataType: "json",
        contentType: 'application/json; charset=utf-8',
        error: function (xhr, ajaxOptions, thrownError) {
            console.log(xhr.status + " \n" + xhr.responseText, "\n" + thrownError);
        },
        success: function (response) {
            if (response.d) {
                listHorarios($("#txtMedico").val());
                alert("Registro actualizado de manera correcta.");
            } else {
                alert("No se pudo actualizar el registro.");
            }
        }
    });
});
