function showHeader(){
const up_to_selects=document.querySelector('.fa-arrow-up')
const cart=document.querySelector('.cart')
const quantity=cart.querySelector('.quantity');



up_to_selects.addEventListener('click',function(){
    // di chuyen den diem x va y
    window.scrollTo(0,0)
})
// ------------ show


if (quantity.textContent==0){
    quantity.style.display='none';
}else{
    quantity.style.display='block';
}


// ----- click
cart.querySelector('i').addEventListener('click',function(){
    window.location="http://localhost:9090/cart/sp"
})

}
showHeader()
// Xu ly liet ke danh sach
// --------Khai bao
const VtriID=[{
    vitri:0,
    ten_vitri:'new_product'
},
{
    vitri:0,
    ten_vitri:'drinks_product'
},
{
    vitri:0,
    ten_vitri:'food_1_product'
},
{
    vitri:0,
    ten_vitri:'food_2_product'
}]
const total=3;
const btn_left=document.querySelectorAll('.btn_left');
const btn_right=document.querySelectorAll('.btn_right');
// --------- Xu ly
    btn_right.forEach((value,index)=>{
        value.addEventListener('click',function(){
            if (value.parentNode.id == VtriID[index].ten_vitri){
                VtriID[index].vitri++;
                handle(VtriID);
            }
        })
    })
    btn_left.forEach((value,index)=>{
        value.addEventListener('click',function(){
            if (value.parentNode.id == VtriID[index].ten_vitri){
                VtriID[index].vitri--;
                handle(VtriID);
            }
        })
    })
function handle(vt=VtriID){
    checkExistValue()
    checkExistButton()
}
function checkExistValue(){
    VtriID.forEach(function(value){
        const container = document.getElementById(value.ten_vitri).querySelectorAll('.container')
        container.forEach(function(a,index){
            if (index + 1> value.vitri+total){
                a.style.display='none'
            }else if (value.vitri>=0 && index<value.vitri){
                if (value.vitri === 0){
                    a.style.display='block'
                }else{
                    a.style.display='none'
                }
            }else{
                a.style.display='block'
            }
        })
    })
}
function checkExistButton(){
    btn_left.forEach(function(value,index){

        if (value.parentNode.id === VtriID[index].ten_vitri){
            if(VtriID[index].vitri === 0){
                value.classList.value='hide'

            }else{
                value.classList.value=''
            }
        }

    })

    btn_right.forEach(function(value,index){
        if (value.parentNode.id == VtriID[index].ten_vitri){

            if(VtriID[index].vitri+total === value.parentNode.querySelectorAll('.container').length){
                value.classList.value='hide'
            }else if(value.parentNode.querySelectorAll('.container').length <= 3){
                value.classList.value='hide'
            }else{
                 value.classList.value=''
            }
        }

    })
}
checkExistValue()
checkExistButton()
// khai bao xu ly luu vao gio hang

 const btn_food_2=document.querySelectorAll('.btn_food_2')
    const btn_food_1=document.querySelectorAll('.food_1_product')
    const btn_drink=document.querySelectorAll('.btn_drink')
    const btn_new=document.querySelectorAll('.btn_new')




    btn_food_2.forEach((value)=>{
            value.addEventListener('click',function(){
            addIntoCart(value)
            })
        })
    btn_food_1.forEach((value)=>{
            value.addEventListener('click',function(){
            addIntoCart(value)
            })
    })
    btn_drink.forEach((value)=>{
                value.addEventListener('click',function(){
                addIntoCart(value)
                })
        })
    btn_new.forEach((value)=>{
               value.addEventListener('click',function(){
               addIntoCart(value)
                })
            })

function addIntoCart(node){
   const cart=document.querySelector('.cart')
   const quantity=cart.querySelector('.quantity');
   const maMonAn= node.parentNode.querySelector('input').value

   var request=new XMLHttpRequest();
   request.open("get","http://localhost:9090/api/add/"+maMonAn)
   request.setRequestHeader("content-type","application/json")
   request.send()
   quantity.textContent=parseInt(quantity.textContent)+1;
   showHeader()
}

