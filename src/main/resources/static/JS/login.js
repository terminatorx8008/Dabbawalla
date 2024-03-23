document.getElementById('registerUserEmail').addEventListener('input', () => {
    const email = document.getElementById('registerUserEmail').value;
    const otp = document.getElementById('otp-label');
    otp.style.display = "block";
    otp.parentElement.style.gridTemplateColumns = '10% 80% 10%';
});
$(document).ready(function() {
    $('#otp-label').click(function() {
        const email = $('#registerUserEmail').val();
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
                $('#registerUserEmail').innerText = '';
                // You can handle errors here, such as displaying an error message to the user
            }
        });
        $('#otp-label').prop('disabled', true);
    });
});
$('#check-otp').click(function() {
    const email = $('#registerUserEmail').val();
    const otp = $('#otp').val();
    // Perform AJAX request
    $.ajax({
        type: 'POST',
        url: '/verify-otp', // Replace with your server-side endpoint
        data: { email: email, otp: otp },
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
});
$(document).ready(function () {
    $("input").keyup(function () {
        if (this.value.length == this.maxLength) {
            $(this).next('input').focus();
        }
    });

});
