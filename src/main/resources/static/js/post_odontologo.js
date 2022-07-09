window.addEventListener("load", function(){

const formulario= document.querySelector("#nuevoOdontologo");
formulario.addEventListener('submit', function(event){

        const data= {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value
        };
        const url= '/odontologos';
        const settings ={
            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
           },
           body: JSON.stringify(data),
        }

        fetch(url,settings)
        .then(response => response.json())
        .then(data=>{
            let mensaje= '<div class= "">' + '<button type= "button" class= "close"></button>' + '<strong> Odontologo Agregado Correctamente </strong></div>'

        document.querySelector('#response').innerHTML= mensaje;
        document.querySelector('#response').style.display= "block";
        resetUploadForm();

        })
        .catch(error=>{
            let error= '<div ' + '<button type="button" class="close"></button>' +  '<strong> Error: No se pudo agregar odontologo</strong></div>'

        document.querySelector('#response').innerHTML= error;
        document.querySelector('#response').style.display= "block";
        resetUploadForm();

        });
        })
 function resetUploadForm(){
    document.querySelector('#nombre').value="";
    document.querySelector('"apellido').value="";
        }


(function(){
    let pathname = window.location.pathname;
    if(pathname== "/"){
           document.querySelector(".nav .nav-item a:first").addClass("active");
       }else if(pathname== "/odontologoList.html"){
       document.querySelector(".nav .nav-item a:last").addClass("active")
       }
})()




})