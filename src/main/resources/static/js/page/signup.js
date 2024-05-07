function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            var addr;

            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            document.getElementById('addressNumber').value = data.zonecode;
            document.getElementById("address").value = addr;
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

document.getElementById("signupForm").addEventListener("submit", function (event) {
    event.preventDefault();
    var formData = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        name: document.getElementById('name').value,
        nickName: document.getElementById('nickname').value,
        birthDate: new Date(document.getElementById('birthDate').value).toISOString().split('T')[0],
        phoneNumber: document.getElementById('phoneNumber').value,
        addressNumber: document.getElementById('addressNumber').value,
        address: document.getElementById('address').value,
        addressDetail: document.getElementById('addressDetail').value
    };

    var jsonData = JSON.stringify(formData);

    fetch('/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
});
