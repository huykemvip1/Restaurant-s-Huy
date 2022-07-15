const content=document.querySelector('.content')
const container_img=document.querySelector('.container_img')
const home=document.getElementById('home')
function activeScroll(){
    if (home.getBoundingClientRect().top >= -600){
            content.classList.remove('hide');
            container_img.classList.remove('hide');
        }else{
            content.classList.add('hide');
            container_img.classList.add('hide');
        }
}
window.addEventListener('scroll',activeScroll)

// Xu ly liet ke gia tri

function xuly(i){
const total=3;
const value=document.querySelectorAll('.comment_1')
const btn_right=document.querySelector('.btn_right')
const btn_left=document.querySelector('.btn_left')


// xu ly hien thi san pham
function hide(){
    value.forEach(function(a,index){
               if (index + 1> i+total){
                    a.style.display='none'
                }else if (i>=0 && index<i){
                    if (value.vitri === 0){
                        a.style.display='block'
                    }else{
                        a.style.display='none'
                    }
                }else{
                    a.style.display='block'
                }
    })
}
hide();
function btn(){
     if (i === 0 && value.length === i+total){
             btn_right.classList.add('hide')
              btn_left.classList.add('hide')
      }else if (value.length === i+total){
          btn_right.classList.add('hide')
          btn_left.classList.remove('hide')
      }else if(i === 0){
          btn_left.classList.add('hide')
          btn_right.classList.remove('hide')
      }else{
         btn_right.classList.remove('hide')
         btn_left.classList.remove('hide')
      }
  }
btn();
  btn_right.addEventListener('click',function(){
    i++
    xuly(i)
    btn()
  })
  btn_left.addEventListener('click',function(){
    i--
    xuly(i)
    btn()
  })

}
xuly(0);


