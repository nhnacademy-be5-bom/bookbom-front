var newNumber = 1;
var wrapperList = JSON.parse(document.getElementById('wrapperData').dataset.wrapperList);


function addWrapper() {

    var wrappers = document.querySelectorAll('.wrappers > .select');
    var totalSelectedQuantity = 0;
    var bookQuantity = 0;

    // beforeOrderBookResponseList에서 각 bookResponse의 quantity를 합산
    beforeOrderBookResponseList.forEach(function (bookResponse) {
        bookQuantity = bookResponse.quantity;
    });

    // 각 포장지에서 선택된 수량을 합산
    wrappers.forEach(function (wrapper) {
        var quantityInput = wrapper.querySelector('input[name="quantity"]');
        var quantity = parseInt(quantityInput.value);
        totalSelectedQuantity += quantity;
    });

    // 주문한 책의 수량과 선택된 포장지의 수량을 비교하여 알림 메시지 표시
    if (totalSelectedQuantity >= totalOrderCount) {
        alert("주문 하신 책의 수량보다 많습니다. 다시 수량을 조절해주세요.");
        return;
    }
    var quantityInput = document.createElement('input');
    quantityInput.setAttribute('type', 'number');
    quantityInput.setAttribute('name', 'quantity');
    quantityInput.setAttribute('min', '1');
    quantityInput.setAttribute('style', 'width: 40px');
    quantityInput.id = 'quantity' + newNumber; // 고유한 ID 생성
    quantityInput.addEventListener('change', updateWrapCountAndCost); // 수량 변경 시 업데이트

    var newWrapper = document.createElement('div');
    newWrapper.classList.add('select');
    newNumber++;

    var wrapperContent = `
    <div style="margin: 10px">
        <input type="text" class="number" name="number" value="${newNumber}" readonly style="width: 20px; text-align: center">
`;
// wrapperList 배열을 순회하면서 각 wrapper에 대한 라디오 버튼과 라벨을 생성
    wrapperList.forEach(function (wrapper) {
        wrapperContent += `
        <div>
            <input type="radio" id="wrapper${wrapper.id}" name="wrapper" value="${wrapper.id}"> 
            <label for="wrapper${wrapper.id}">${wrapper.name}</label>
        </div>
    `;
    });

    wrapperContent += '<input type="number" id="quantity" name="quantity" min="1" style="width: 40px"></div>'; // wrapperContent 마무리

    newWrapper.innerHTML = wrapperContent;
    newWrapper.appendChild(quantityInput); // 수량 입력 필드 추가
    var firstWrapper = document.querySelector('.wrappers');
    firstWrapper.appendChild(newWrapper);

    // + 버튼을 누르면 입력 필드를 비활성화
    quantityInput.disabled = true;

    // 선택한 포장지의 가격을 고려하여 wrapBookCount와 totalWrapCost 업데이트
    updateWrapCountAndCost();
}

function removeWrapper() {
    var wrappers = document.querySelectorAll('.wrappers > .select');
    if (wrappers.length > 1) {
        // 제거되는 포장지의 수량 값을 가져와서 newNumber를 갱신
        var lastWrapper = wrappers[wrappers.length - 1];
        var numberInput = lastWrapper.querySelector('.number');
        var removedNumber = parseInt(numberInput.value);
        newNumber = removedNumber - 1;

        lastWrapper.remove();
    } else {
        alert("최소 한 개의 포장지를 유지해야 합니다.");
    }
    var quantityInput = document.createElement('input');
    quantityInput.setAttribute('type', 'number');
    quantityInput.setAttribute('name', 'quantity');
    quantityInput.setAttribute('min', '1');
    quantityInput.setAttribute('style', 'width: 40px');
    quantityInput.id = 'quantity' + newNumber; // 고유한 ID 생성
    quantityInput.addEventListener('change', updateWrapCountAndCost); // 수량 변경 시 업데이트
    // - 버튼을 누르면 입력 필드를 다시 활성화
    quantityInput.disabled = false;

    // 선택한 포장지의 가격을 고려하여 wrapBookCount와 totalWrapCost 업데이트
    updateWrapCountAndCost();
}

function updateWrapCountAndCost() {
    // wrapBookCount 및 totalWrapCost 업데이트를 위해 필요한 변수 초기화
    var totalQuantity = 0;
    var totalCost = 0;

    // 모든 포장지에 대해 반복하여 수량 및 가격을 계산
    var wrappers = document.querySelectorAll('.wrappers > .select');
    wrappers.forEach(function (wrapper) {
        var quantityInput = wrapper.querySelector('input[name="quantity"]');
        var selectedWrapperName = wrapper.querySelector('input[name="wrapper"]:checked').value;
        var quantity = parseInt(quantityInput.value);

        // 선택한 포장지의 가격 가져오기
        var selectedWrapper = wrapperList.find(function (wrapper) {
            return wrapper.name === selectedWrapperName;
        });
        var wrapperCost = selectedWrapper.cost;

        // 수량 및 가격을 합산
        totalQuantity += quantity;
        totalCost += quantity * wrapperCost;
    });

    // wrapBookCount 및 totalWrapCost 업데이트
    var wrapBookCountElement = document.querySelector('.wrapBookCount');
    wrapBookCountElement.innerText = totalQuantity + '권';

    var totalWrapCostElement = document.querySelector('.totalWrapCost');
    totalWrapCostElement.innerText = totalCost.toLocaleString() + '원';
}

// '보기' 버튼을 클릭할 때마다 테이블 요소를 토글하는 함수
function toggleTable() {
    var table = document.querySelector('table'); // 테이블 요소 선택
    if (table.style.display === 'none' || table.style.display === '') {
        table.style.display = 'block'; // 테이블을 보이도록 설정
    } else {
        table.style.display = 'none'; // 테이블을 숨기도록 설정*
    }
}

function redirectToURL(url) {
    window.location.href = url; // 해당 URL로 이동
}
