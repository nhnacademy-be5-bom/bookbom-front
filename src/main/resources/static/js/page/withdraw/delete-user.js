document.addEventListener("DOMContentLoaded", function() {
    var submitButton = document.getElementById("submit");

    submitButton.addEventListener("click", function(event) {
        event.preventDefault();
        var withdrawAgree = document.getElementById('withdrawAgree').checked;
        var deleteAgree = document.getElementById('deleteAgree').checked;
        var checkboxes = document.querySelectorAll(".infoBox input[type='checkbox']:checked");

        if (!withdrawAgree || !deleteAgree) {
            alert('필수 항목에 모두 동의해주세요.');
            return false;
        }

        var checkedCount = 0;

        checkboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                checkedCount++;
            }
        });
        if (checkedCount === 0) {
            alert('사유를 선택해주세요.');
            return false;
        }

        var formData = new FormData();

        checkboxes.forEach(function(checkbox,index) {
            formData.append('reasons[' + index + ']', checkbox.value);
        });

        fetch("/users/withdraw", {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert("탈퇴가 완료되었습니다.");
                    window.location.href = "/";
                } else {
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
