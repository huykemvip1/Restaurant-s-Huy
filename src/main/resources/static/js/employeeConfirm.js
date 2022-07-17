const check=document.querySelectorAll('.check')
const mon_an=document.querySelectorAll('.mon_an')

check.forEach((value,index) => {
     value.addEventListener('click',function(){
             showMonAn(value)

     })
})

document.addEventListener('click',function(event){
    if (event.target === document.querySelector('body') ||
            event.target === document.querySelector('.content')){
        mon_an.forEach((value) =>{
           value.style.display='none'
        })
    }
})

function showMonAn(value){
    const mon_an =value.parentNode.querySelectorAll('.mon_an')
    mon_an.forEach((vl) =>{
        vl.style.display='flex'
    })
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


// Xu ly logic xac nhan va huy

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
    http.send(obj)
}


