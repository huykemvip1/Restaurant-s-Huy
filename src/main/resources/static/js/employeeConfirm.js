const check=document.querySelectorAll('.check')
const mon_an=document.querySelectorAll('.mon_an')
const mon_an_container=document.querySelectorAll('.mon_an_container')

check.forEach((value,index) => {
     value.addEventListener('click',function(){
             showMonAn(value)
     })
})

document.addEventListener('click',function(event){
    if (event.target === document.querySelector('body') ||
            event.target === document.querySelector('.content')){
        mon_an_container.forEach((value) =>{
           value.style.display='none'
        })
    }
})

function showMonAn(value){
    const vl =value.parentNode.querySelector('.mon_an_container')
    vl.style.display='block'
}

const khachHang={
    ten:'',
    sdt:'',
    thoiGianDat:''
}



const account=document.querySelector('.account')
const content=document.querySelector('.content')

const name_customer=content.querySelectorAll('.name_customer')
const phone_customer=content.querySelectorAll('.phone_customer')
const date_customer=content.querySelectorAll('.date_customer')


// buntton xac nhan vs huy don hang cua khach

// Xu ly logic xac nhan va huy

const accept=document.querySelectorAll('.accept')
accept.forEach((value,index) =>{
    value.addEventListener('click',function(){
        xacNhanKH(index)
    })
})

const cancel=document.querySelectorAll('.cancel')
cancel.forEach((value,index) =>{
    value.addEventListener('click',function(){
        huyKH(index)
    })
})


// xu ly phan json
function xuLyObjectToJson(index) {
    khachHang.sdt=phone_customer[index].textContent
    khachHang.ten=name_customer[index].textContent
    khachHang.thoiGianDat=date_customer[index].textContent
    phone_customer[index].parentNode
            .parentNode
            .removeChild(phone_customer[index].parentNode)
    return JSON.stringify(khachHang)
}


// connect vs server
function connectServer(method,url){
    var http=new XMLHttpRequest()
    http.open(method,url)
    return http;
}




function xacNhanKH(index){


    var http=connectServer("POST","http://localhost:9090/api/employee/xac_nhan/"+account.textContent)
    http.setRequestHeader("content-type","application/json")


    var obj=xuLyObjectToJson(index)
    http.send(obj)
}


function huyKH(index){
    var http=connectServer("POST","http://localhost:9090/api/employee/huy/"+account.textContent)
    http.setRequestHeader("content-type","application/json")

    var obj=xuLyObjectToJson(index)
    console.log(khachHang)
    http.send(obj)
}


