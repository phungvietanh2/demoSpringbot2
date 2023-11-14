function showPasswordForm() {
    document.getElementById('username-form').classList.add('hidden');
    document.getElementById('password-form').classList.remove('hidden');
}

function checkPasswordMatch() {
    var password1 = document.getElementById('password1').value;
    var password2 = document.getElementById('password2').value;
    var errorElement = document.getElementById('password-error');

    if (password1 === password2) {
        // Mật khẩu trùng khớp, xóa thông báo lỗi
        errorElement.innerHTML = '';
    } else {
        // Mật khẩu không trùng khớp, hiển thị thông báo lỗi
        errorElement.innerHTML = 'Passwords do not match. Please try again';
        errorElement.style.display = 'block';
    }
}