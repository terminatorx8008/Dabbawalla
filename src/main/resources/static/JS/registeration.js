const passwordInput = document.getElementById("password");
const confirmPasswordInput = document.getElementById("confirm-password");
const emailInput = document.getElementById("email");
const mess_numberInput = document.getElementById("mess-number");

confirmPasswordInput.disabled = true;
passwordInput.addEventListener("input", () => {
    const password = passwordInput.value;
    const strength = document.getElementById("password-strength");
    const mess = document.getElementById("password-message");
    var numbers = /[0-9]/g;
    var upperCaseLetters = /[A-Z]/g;
    var lowerCaseLetters = /[a-z]/g;
    var specialSymbol = /[@,#,$,%,&,*]/g;
    let message = "";

    if (password.length < 4 || password.length > 16|| !numbers.test(password) || !upperCaseLetters.test(password) || !lowerCaseLetters.test(password) || !specialSymbol.test(password) || password.includes(" ")) {
        strength.className = "weak";
        strength.textContent = "Weak";
        confirmPasswordInput.disabled = true;
        message = "1. Password should be between 4 to 16 characters<br/>2. should contain atleast: <ol><li>one Uppercase letter</li><li>one Lowercase letter</li><li>one Number</li><li>one Special Symbol</li></ol>3. Should not contain any spaces";
    }else {
        strength.className = "strong";
        strength.textContent = "Strong";
        confirmPasswordInput.disabled = false;
    }
    mess.innerHTML = message;
});

mess_numberInput.addEventListener("input", () => {
    const mess_number = mess_numberInput.value;
    const mess_numberHelp = document.getElementById("mess-number-help");
    if (mess_number.length !== 10) {
        mess_numberHelp.textContent = "Please enter a valid 10 digit number.";
        mess_numberHelp.className = "error";
    } else {
        mess_numberHelp.textContent = "Valid Number";
        mess_numberHelp.className = "success";
    }
});



confirmPasswordInput.addEventListener("input", () => {
    const confirmPassword = confirmPasswordInput.value;
    const password = passwordInput.value;
    const confirmPasswordHelp = document.getElementById("confirm-password-help");
    if (passwordInput.value === "") {
        confirmPasswordHelp.textContent = "Please first enter password above.";
        confirmPasswordHelp.className = "error";
    } else {
        if (confirmPassword !== password) {
            confirmPasswordHelp.textContent = "Passwords do not match.";
            confirmPasswordHelp.className = "error";
        } else {
            confirmPasswordHelp.textContent = "Passwords match.";
            confirmPasswordHelp.className = "success";
        }
    }
});
isvalidemail = false;
emailInput.addEventListener("input", () => {
    const email = emailInput.value;
    const emailMessage = document.getElementById("email-message");
    if (email.includes("@") && email.includes(".")) {
        emailMessage.textContent = "Valid Email";
        emailMessage.className = "success";
        isvalidemail = true;
    } else {
        emailMessage.textContent = "Invalid Email";
        emailMessage.className = "error";
    }
});

checkOtp = () => {
    const otp = document.getElementById("form").querySelectorAll("input");
    let otpValue = "";
    otp.forEach((input) => {
        otpValue += input.value;
    });
    if (otpValue.length === 6) {
        const email = document.getElementById("email").value;
        // Perform AJAX request
        $.ajax({
            type: 'POST',
            url: '/verify-otp', // Replace with your server-side endpoint
            data: { email: email, otp: otpValue },
            success: function(response) {
                console.log('OTP verified successfully:', response);
                alert('OTP verified successfully.');
                $('#signup-btn').prop('disabled', false);
                // You can handle the response here, such as showing a success message to the user
            },
            error: function(xhr, status, error) {
                console.error('Error verifying OTP:', error);
                alert('Error verifying OTP. Please try again.');
                // You can handle errors here, such as displaying an error message to the user
            }
        });
    } else {
        alert("Please enter the complete OTP");
    }
}
$(document).ready(function () {
    $("input").keyup(function () {
        if (this.value.length == this.maxLength) {
            $(this).next('input').focus();
        }
    });
    $("#otp").click(function () {
        if(isvalidemail) {
            document.querySelector(".otp").style.display = "block";
            const email = $('#email').val();
            // Perform AJAX request
            $.ajax({
                type: 'POST',
                url: '/send-otp', // Replace with your server-side endpoint
                data: { email: email },
                success: function(response) {
                    console.log('OTP sent successfully:', response);
                    alert('OTP sent successfully. Please check your email.');
                    // You can handle the response here, such as showing a success message to the user
                },
                error: function(xhr, status, error) {
                    console.error('Error sending OTP:', error);
                    alert('Error sending OTP. Please try again.');
                    $('#email').innerText = '';
                    // You can handle errors here, such as displaying an error message to the user
                }
            });
            $('#otp-label').prop('disabled', true);
        }
    });
});
