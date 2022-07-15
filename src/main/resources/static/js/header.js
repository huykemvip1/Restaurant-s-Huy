
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

// ----- click
cart.querySelector('i').addEventListener('click',function(){
    window.location="http://localhost:9090/cart/sp"
})



