const codeInput = document.getElementById("code");
const tickIcon = document.querySelector(".tick-icon");
const userIdInput = document.getElementById("userId");
const errorMessage = document.getElementById("message");
codeInput.addEventListener("blur", function() {
    const codeValue = codeInput.value;

    $.ajax({
        url: 'api/myhome_code',
        type:'GET',
        data: {code: codeValue},
        success: function (data) {
            const userWithCode = data.find(user => user.users_code === codeValue);
            if (userWithCode) {
                tickIcon.style.display = "block";
                errorMessage.textContent = "";
                userIdInput.value = userWithCode.user_id;
            } else {

                tickIcon.style.display = "none";
                errorMessage.textContent = "Mã code không hợp lệ";
                errorMessage.classList.add("error-message-show");
            }

        },
        error: function () {
            console.error('API request failed');
        }
    })


});


function renderMyHome(data){
    console.log(data);
}