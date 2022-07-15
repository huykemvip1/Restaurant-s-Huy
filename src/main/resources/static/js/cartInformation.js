

const progress=document.querySelector('.progress')
const step_2=document.querySelector('.step_2')
const step_1=document.querySelector('.step_1')


function showPositionProgress(){
    progress.style.background='green'
    step_2.style.background='green'
    step_1.style.background='green'
    window.scrollTo(0,63);
}

showPositionProgress();





// Xu ly loi o form

const customer=document.querySelector('#customer')

const fullname = customer.querySelector('#fullname')
const phone = customer.querySelector('#phone')
const email = customer.querySelector('#email')
const number_bank=customer.querySelector('#number_bank')




const input=customer.querySelectorAll('input')

function showError(message,text){
   message.textContent = text
   message.style.color = 'red'
   message.style.display='block'
}

const checkError={
    required:false,
    fullname: false,
    phone:false,
    email:false,
    number_bank:false
}

function isBtnDisabled(){
     const btn_continue=document.querySelector('.btn_continue')
         if(checkError.fullname === true &&
         checkError.phone === true && checkError.email === true
         && checkError.fullname === true && checkError.required === true){
                     btn_continue.disabled=false
         }else{
                     btn_continue.disabled=true
         }
}

isBtnDisabled()

function checkDisable(){
    input.forEach(function(element){
         const message=element.parentNode.querySelector('p')
         isRequired(input,message,element)
    })
    console.log(checkError)
    isBtnDisabled()
}


// Kiem tra cac yeu cau
function isRequired(input,message,element){
         let check=false

      if (element.value === undefined || element.value === ''){
                      showError(message,"Không được bỏ trống")
                      check=false
                      checkError.required=false
                }else{
                      message.style.display='none'
                      check=true
                      checkError.required=true
                }
                if ( check === true){
                    switch(element.parentNode.id){
                           case "phone" : limitPhone(message,element.value)
                           break;
                           case "email" : litmitEmail(message,element.value)
                           break;
                           case "fullname" : litmitFullName(message,element.value)
                           break;
                           case "number_bank" : litmitNumberBank(message,element.value)
                           break;
                             }
                          }
}

input.forEach(function(element){

     const message=element.parentNode.querySelector('p')
     element.addEventListener('blur',function(){

          checkDisable()


     })
})





// Gioi han cua thong tin ve so dien thoai

function limitPhone(message,value,isBlur){



       if(value.length === 10){
          if (value.match(/[0-9]{10}/) !== null){
               message.style.display='none'
               checkError.phone=true

          }else{
               showError(message,"Chỉ chứa số")
                checkError.phone=false
          }
       }else{
          showError(message,"Số điện thoại có chiều dài bằng 10")
           checkError.phone=false
       }

}


// Gioi han cua thong tin ve email

function litmitEmail(message,value){
    console.log(value)
       if (value.match(/\w{3,}@gmail.com/) !== null){
           message.style.display='none'
           checkError.email=true

       }else{
           showError(message,"Tài khoản gmail bạn đã sai: 1234@gmail.com")
           checkError.email=false
       }

}

// Gioi han cua thong tin ve ten day du
function litmitFullName(message,value){
console.log(value)
      if (value.match(/\w{2,6}\s\w{2,}\s\w{2,6}/) !== null){
            message.style.display='none'
            checkError.fullname=true
      }else{
            showError(message,"Tên của bạn đã sai: Lê Lê Anh")
            checkError.fullname=false
      }

}

// Gioi han cua thong tin ve so tai khoan ngan hang
function litmitNumberBank(message,value){
     if (value.length === 12){
        if (value.match(/[0-9]{12}/) !== null){
             message.style.display='none'
             checkError.number_bank=true
        }else{
              showError(message,"Số tài khoản ngân hàng của bạn sai")
               checkError.number_bank=false
        }
     }else{
         showError(message,"Số tài khoản ngân hàng có độ dài bằng 12")
         checkError.number_bank=false
     }

}






