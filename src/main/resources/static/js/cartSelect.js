

const progress=document.querySelector('.progress')

const step_2=progress.querySelector('.step_2')
const step_3=progress.querySelector('.step_3')
progress.style.background='green'
step_2.style.background='green'
step_3.style.background='green'


// Xu ly chon mode
const mode=document.getElementById('mode');
const btn_mode=document.querySelector('.btn_mode')
const select_order=document.getElementById('select_order')
const select_book=document.getElementById('select_book')
btn_mode.addEventListener('click',function(){
    console.log('haha');
    const value=mode.value
    if (value === 'order'){
        select_order.style.display="block"
    }else if(value === 'book'){
        select_book.style.display="block"
    }else{
        return;
    }
    mode.parentNode.style.display='none'
})

// xu ly phan book dat ban
const gioAn=document.querySelector("select[name='thoiGianSuDung']")
console.log(gioAn)
gioAn.addEventListener('change',function(){
    sendData()
})

const mang=[{
    maBan:'',
    thoiGianSuDung:'',
    tenBan:'',
    soLuongNguoi:0
}]

function sendData(){

mang.forEach(function(value,index){
    mang.pop()
})

console.log(mang)
const soLuong=document.querySelector("select[name='soLuongNguoi']")
const option=soLuong.querySelectorAll("option")
soLuong.innerHTML=''
var http=new XMLHttpRequest();
http.open('get','http://localhost:9090/api/ds/'+gioAn.value)
http.responseType ="json"
http.onreadystatechange= function(){
    if (http.readyState === 4){
        for (let index = 0; index < http.response.length; index++){
            mang[index] =http.response[index];
        }
         for (let i =0 ;i<mang.length;i++){
            soLuong.innerHTML+=`<option value=${mang[i].soLuongNguoi}>${mang[i].soLuongNguoi}</option>`
        }
    }
}

http.send()

}

