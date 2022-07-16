
const fullname=document.getElementById('fullname')
const sdt=document.getElementById('phone')

// blur sdt va full name
sdt.querySelector('input').addEventListener('blur',xuLyData)
fullname.querySelector('input').addEventListener('blur',xuLyData)

// goi doi tuong khach hang
const khachHang=[{
   ten:'',
   sdt:'',
   monAn:{
    ten:'',
    maMonAn:'',
   }
}]


// xu ly du lieu dua vao input
function xuLyData(){
    const input_sdt= sdt.querySelector('input')
    const input_fullname= fullname.querySelector('input')
    if (input_fullname.value !== ''
        && input_sdt.value !== ''){
           khachHang[0].ten=input_fullname.value
           khachHang[0].sdt=input_sdt.value
            sendData()
        }

}


// gui du lieu den server rest api

function sendData(){
    var obj=JSON.stringify(khachHang)
    var http=new XMLHttpRequest();
    http.open('post',"http://localhost:9090/api/comment_data")
    http.setRequestHeader("content-type","application/json")
    http.responseType='json'
    http.onreadystatechange=function(){
        if (http.readyState === 4){
           xuLyShow(http.response)
        }
    }
    http.send(obj)
}

// xu ly hien thi vs gia tri gan vao form
function xuLyShow(data){
    const infor_food=document.querySelector('.infor_food')
    const message=document.querySelector('form').querySelector('p')
     if(data == null){
        infor_food.style.display='none'
        message.style.display='block'
     }else{
        infor_food.style.display='block'
        message.style.display='none'

        for(let index=0;index < data.length;index++){
            khachHang.push(
                {
                    ten: data[index].ten,
                    sdt: data[index].sdt,
                    monAn:{
                        ten: data[index].monAn.ten,
                        maMonAn: data[index].monAn.maMonAn
                    }
                }
            )
        }
        khachHang.shift()
        const input_sdt= sdt.querySelector('input')
        const input_fullname= fullname.querySelector('input')
        fullname.querySelector('label').innerHTML+=`<br><h4>${input_fullname.value}</4>`
        sdt.querySelector('label').innerHTML+=`<br><h4>${input_sdt.value}</4>`
        input_sdt.style.display='none'
        input_fullname.style.display='none'
     }

     const select=infor_food.querySelector('#doAn').querySelector('select')
     select.innerHTML=''
     for(let data of khachHang){
        select.innerHTML+=`<option value=${data.monAn.maMonAn}>${data.monAn.ten}</option>`
     }
}



const labels=document.getElementById('soSao').
          querySelector('span').querySelectorAll('label');


labels.forEach(function(element,index){


    let check= true
    let value= element.getAttribute('for')
    const input_value=element.parentNode.querySelector(`input[class=${value}]`)



    element.addEventListener('click',function(){
        for (let i=0;i < labels.length;i++){
            let for_value= labels[i].getAttribute('for')
            const check_value=labels[i].parentNode.querySelector(`input[class=${for_value}]`)

            if (i !== index){
                    check_value.removeAttribute("checked")
            }
            console.log(check_value);
        }
        input_value.setAttribute("checked",true)
    })

})



