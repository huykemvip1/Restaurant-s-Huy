
const up_to_selects=document.querySelector('.fa-arrow-up')
up_to_selects.addEventListener('click',function(){
    // di chuyen den diem x va y
    window.scrollTo(0,0)
})

// ------------ show
const cart=document.querySelector('.cart')
const quantity=cart.querySelector('.quantity');
if (quantity.textContent==0){
    quantity.style.display='none';
}else{
    quantity.style.display='block';
}


const step_1=document.querySelector('.step_1')
function showPositionProgress(){
    step_1.style.background='green'
    window.scrollTo(0,63)
}
showPositionProgress()

const products_customer=document.querySelectorAll('.products_customer')


function showOrHideProduct(){
   products_customer.forEach(function(node){
      checkValue(node)
   })
   sum = 0
}

// Xu ly su ton tai cua button

function checkValue(node){
         const value=  node.querySelectorAll('input')[1]
         const ten_sp_ct= node.querySelector('h4')
         const btn_quantity=node.querySelector('.btn_quantity')

         const gia_tien=node.querySelector('h5').querySelector('span')


         const btn_right_1=btn_quantity.querySelector('.btn_right')
         const btn_left_1=btn_quantity.querySelector('.btn_left')

         const select_value = btn_quantity.querySelector('h5')
         const so_luong_chon = parseInt(select_value.textContent)

         if (so_luong_chon === 1 ){
                     btn_left_1.style.display = 'none'
         }else{
            if(parseInt(value.textContent) === so_luong_chon){
               btn_right_1.style.display='none'
            }else{
               btn_right_1.style.display='block'
            }
                 select_value.textContent=so_luong_chon
                 btn_left_1.style.display = 'block'

         }
         tinh_tong_tien(so_luong_chon,ten_sp_ct,parseInt(gia_tien.textContent))

}
// Xu ly button right va left

   const btn_right=document.querySelectorAll('.btn_right')
   const btn_left=document.querySelectorAll('.btn_left')



   btn_right.forEach(function(value) {
       value.addEventListener('click',function(){
               const el_tong_tien_sp_chon = value.parentNode.querySelector('h5').textContent
               so_luong_chon=parseInt(el_tong_tien_sp_chon);
               so_luong_chon = so_luong_chon+1
               value.parentNode.querySelector('h5').textContent = so_luong_chon
               showOrHideProduct()
        })
   })

   btn_left.forEach(function(value){
     value.addEventListener('click',function(){
                const el_tong_tien_sp_chon = value.parentNode.querySelector('h5').textContent
                so_luong_chon = parseInt(el_tong_tien_sp_chon);
                so_luong_chon = so_luong_chon-1;
                value.parentNode.querySelector('h5').textContent = so_luong_chon
                showOrHideProduct()
        })
   })


// Xu ly ben cot tinh tien
let sum = 0
function tinh_tong_tien(so_luong_chon,ten_sp_ct,gia_tien){
   const tong_tien_sp = document.querySelectorAll('.tong_tien_sp')

   if (parseInt(so_luong_chon) > 0){



         tong_tien_sp.forEach(function(node){
            const ten_sp=node.parentNode.querySelector('.ten_sp')
             const so_luong = node.parentNode.querySelector('.so_luong')

            if(ten_sp_ct.textContent === ten_sp.textContent){
              so_luong.textContent=so_luong_chon
              let tong_tien_sp_chon = parseInt(so_luong.textContent)*parseInt(gia_tien)
              sum += tong_tien_sp_chon
              node.textContent = tong_tien_sp_chon

            }
         })
          const tong_tien = document.getElementById('tong_tien')
          tong_tien.textContent = sum

   }

}
// Xu ly luu du lieu vao server
DataCart= [{
   sessionId:'',
   maMonAn:'',
   soLuong:1,
}]



const btn_confirm=document.querySelector('.btn_confirm')
btn_confirm.addEventListener('click',addIntoCart)
function addIntoCart(){
  products_customer.forEach(function(value){
      const so_luong = value.querySelector('.btn_quantity').querySelector('h5').textContent;
      if (parseInt(so_luong) > 0){
          const ma_mon_an= value.querySelectorAll('input')[0].value
          console.log(ma_mon_an)
          if (DataCart[0].maMonAn === ''){
              DataCart[0].maMonAn=ma_mon_an
              DataCart[0].soLuong=parseInt(so_luong)
          }else{
              DataCart.push({
                 sessionId:'',
                 maMonAn: ma_mon_an,
                 soLuong: so_luong
              })
          }
      }
  })

   var request=new XMLHttpRequest();
   request.open("post","http://localhost:9090/api/add")
   request.setRequestHeader("content-type","application/json")
   const obj=JSON.stringify(DataCart)
   request.send(obj)

   window.location="http://localhost:9090/cart/information"
}

showOrHideProduct()

