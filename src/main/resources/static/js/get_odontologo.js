window.addEventListener('load',function(){

    (function(){
        const url= '/odontologos'
        const settings= {
            method='GET'
        }
        fetch(url,settings)
        .then(response=> response.json())
        .then(data=>{
            for(odontologo of data){
                var table= document.getElementById("odontologoTable");
                var row= table.insertRow();
                let tr_id= 'tr_' + odontologo.id;

                row.id= tr_id;


                let botonBorrar= '<button' + 'id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' + 'type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger"' + '</button>';
                let botonActualizar='<button' + 'id=' + '\"' + 'btn_id_' + odontologo.id + '\"' + 'type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info"' + odontologo.id + '</button>';

            row.innerHTML= '<td>' + botonActualizar + '</td>' +
                           '<td class=\"td_first_name\">' + odontologo.nombre.toUpperCase() +  '</td>'+
                           '<td class=\"td_last_name\">' + odontologo.apellido.toUpperCase() +  '</td>'+
                           '<td>' + botonBorrar + '</td>';


            }
        })
    })
})