document.addEventListener('DOMContentLoaded', (event) => {
    document.querySelectorAll('.btn-primary').forEach((button) => {
        button.addEventListener('click', function () {
            const row = this.closest("tr");
            const earnType = row.querySelector(".earnType").textContent;
            const earnPoint = row.querySelector(".earnPoint").textContent;
            const id = row.dataset.id;

            const selectBox = document.createElement("select");
            selectBox.className = "form-select";
            let types = [{"text": "금액", "value": "COST"}, {"text": "비율", "value": "RATE"}];
            types.forEach(function (type) {
                let option = document.createElement("option");
                option.value = type.value;
                option.text = type.text;
                selectBox.appendChild(option);
            });
            selectBox.value = earnType === '비율' ? 'RATE' : 'COST';
            row.querySelector(".earnType").innerHTML = '';
            row.querySelector(".earnType").appendChild(selectBox);


            let inputBox = document.createElement("input");
            inputBox.className = "form-control";
            inputBox.type = "number";
            inputBox.value = earnPoint;
            row.querySelector(".earnPoint").innerHTML = '';
            row.querySelector(".earnPoint").appendChild(inputBox);

            this.textContent = '수정 완료';
            this.removeEventListener('click', arguments.callee);
            this.addEventListener('click', function () {
                let updatedEarnType = row.querySelector(".earnType select").value;
                let updatedEarnPoint = row.querySelector(".earnPoint input").value;

                // Validate input
                if (isNaN(updatedEarnPoint) || updatedEarnPoint <= 0) {
                    alert("포인트 적립률은 0보다 커야 합니다.");
                    return;
                }

                // Send fetch request to server
                fetch(`/point-rate/${id}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        earnType: updatedEarnType,
                        earnPoint: updatedEarnPoint,
                    }),
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success:', data);
                        window.location.href = '/admin/point-rate';
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                    });
            });
        });
    });
});
