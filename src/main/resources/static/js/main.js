$(document).ready(function(){

    'use strict';
    $('.panel-control').on('click',function(){

        $('.contenidocitas').hide();
        $('.contenidopac').hide();
        $('.contenidoreg').hide();
    })

    $('.pacientes').on('click',function(){
        $('.contenidopac').show();
        $('.contenidocitas').hide();
        $('.contenidopanel').hide();
        $('.contenidoreg').hide();
    })

    $('.citas').on('click',function(){
        $('.contenidopac').hide();
        $('.contenidocitas').show();
    })
    $('.registropac').on('click',function(){
        $('.contenidoreg').show();
        $('.contenidopac').hide();
        $('.contenidocitas').hide();
        $('.contenidopanel').hide();
    })
    
});
    