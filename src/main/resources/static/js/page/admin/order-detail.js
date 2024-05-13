document.addEventListener("DOMContentLoaded", function () {

    let selectedStatusElement = null;

    document.getElementById('statusList').addEventListener('click', function (event) {
        if (event.target.tagName === 'LI') {
            if (selectedStatusElement !== null) {
                selectedStatusElement.classList.remove('active');
            }
            event.target.classList.add('active');
            selectedStatusElement = event.target;
        }
    });

    document.getElementById('updateButton').addEventListener('click', function () {
        if (selectedStatusElement === null) {
            alert("변경할 상태를 선택하세요.");
            return;
        }

        const selectedStatus = selectedStatusElement.textContent;
        let selectedOrders = [];
        let errorMessage;
        let isInvalid = false;

        const orderId = document.getElementById('updateButton').getAttribute('data-order-id');
        selectedOrders.push(orderId);
        const status = document.querySelector('.order-status').textContent.trim();
        switch (selectedStatus) {
            case '배송중':
                if (status !== '대기') {
                    errorMessage = '"배송중" 상태 변경은 "대기" 상태 주문만 가능합니다.';
                    isInvalid = true;
                }
                break;
            case '완료':
                if (status !== '배송중') {
                    errorMessage = '"완료" 상태 변경은 "배송중" 상태 주문만 가능합니다.';
                    isInvalid = true;
                }
                break;
            case '취소':
                if (status !== '대기') {
                    errorMessage = '"취소" 상태 변경은 "대기" 상태 주문만 가능합니다.';
                    isInvalid = true;
                }
                break;
            case '반품':
                if (status !== '완료') {
                    errorMessage = '"반품" 상태 변경은 "완료" 상태 주문만 가능합니다.';
                    isInvalid = true;
                }
                break;
        }

        if (isInvalid) {
            alert(errorMessage);
            return;
        }

        fetch(`/admin/orders/update-status`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({orderIds: selectedOrders, status: selectedStatus})
        })
            .then(response => {
                return response.json();
            })
            .then(data => {
                if (data.header.resultMessage === "SUCCESS") {
                    alert("변경이 완료되었습니다");
                    window.location.reload();
                } else {
                    console.error("data.header.resultMessage");
                    alert("변경에 실패하였습니다. 나중에 다시 요청해주세요..")
                }
            })
    });
});
