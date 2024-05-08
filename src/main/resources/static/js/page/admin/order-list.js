function getDate(date = new Date()) {
    const UTCDate = date instanceof Date ? date : new Date(date);
    return new Date(UTCDate.getTime() + (9 * 60 * 60 * 1000));
}

function setDateRange(type) {
    const today = getDate();
    today.setHours(0, 0, 0, 0);
    let from = new Date(today);

    switch (type) {
        case 'week':
            from.setDate(today.getDate() - 7);
            break;
        case 'month':
            from.setMonth(today.getMonth() - 1);
            break;
        case 'threeMonths':
            from.setMonth(today.getMonth() - 3);
            break;
        case 'year':
            from.setFullYear(today.getFullYear() - 1);
            break;
    }

    document.getElementById('date_from').value = from.toISOString().split('T')[0];
    document.getElementById('date_to').value = today.toISOString().split('T')[0];
}

function submitDates() {
    const dateFrom = document.getElementById('date_from').value;
    const dateTo = document.getElementById('date_to').value;

    if (!dateFrom || !dateTo) {
        alert("시작 날짜와 종료 날짜를 모두 입력해주세요.");
        return;
    }

    const sort = document.querySelector('input[name="sort_order"]:checked').value;
    const deliveryStatus = document.querySelector('input[name="delivery_status"]:checked').value;

    const dateFromObj = getDate(dateFrom + 'T00:00:00+09:00');
    const dateToObj = getDate(dateTo + 'T00:00:00+09:00');
    const today = getDate();
    today.setHours(0, 0, 0, 0);

    if (dateToObj > today) {
        alert("종료 날짜를 오늘 이후로 설정하실 수 없습니다.");
        return;
    }

    if (dateFromObj > dateToObj) {
        alert("시작 날짜는 종료 날짜보다 이후일 수 없습니다.");
        return;
    }

    const queryParams = `?date_from=${dateFrom}&date_to=${dateTo}&sorted=${sort}&status=${deliveryStatus}`;
    window.location.href = `/admin/orders${queryParams}`;
}

function setRadioButtons() {
    const urlParams = new URLSearchParams(window.location.search);
    const sortOrder = urlParams.get('sorted');
    const deliveryStatus = urlParams.get('status');

    // 정렬 순서 라디오 버튼 설정
    if (sortOrder) {
        document.querySelector(`input[name="sort_order"][value="${sortOrder}"]`).checked = true;
    }

    // 배송 상태 라디오 버튼 설정
    if (deliveryStatus) {
        document.querySelector(`input[name="delivery_status"][value="${deliveryStatus}"]`).checked = true;
    }
}

window.onload = setRadioButtons;


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

        // 선택된 상태와 해당되는 주문 번호 가져오기
        const selectedStatus = selectedStatusElement.textContent;
        const selectedOrders = [];
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let errorMessage;
        let isInvalid = false;
        if (checkboxes.length === 0) {
            alert('상태를 변경할 주문을 선택하세요.');
            return;
        }
        checkboxes.forEach(function (checkbox) {
            const orderId = checkbox.getAttribute('data-order-id');
            const status = checkbox.closest('tr').querySelector('.order-status').textContent.trim();
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
            selectedOrders.push(orderId);
        });
        if (isInvalid) {
            alert(errorMessage);
            return;
        }
        console.log(selectedOrders);
    });
    document.getElementById('updateButton').addEventListener('click', function () {
        if (selectedStatusElement === null) {
            alert("변경할 상태를 선택하세요.");
            return;
        }

        const selectedStatus = selectedStatusElement.textContent;
        const selectedOrders = [];
        const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        let errorMessage;
        let isInvalid = false;
        if (checkboxes.length === 0) {
            alert('상태를 변경할 주문을 선택하세요.');
            return;
        }
        checkboxes.forEach(function (checkbox) {
            const orderId = checkbox.getAttribute('data-order-id');
            const status = checkbox.closest('tr').querySelector('.order-status').textContent.trim();
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
            selectedOrders.push(orderId);
        });
        if (isInvalid) {
            alert(errorMessage);
            return;
        }
        console.log(selectedOrders);

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
