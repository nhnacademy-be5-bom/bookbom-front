var newNumber =1;
function addWrapper() {
    var wrappers = document.querySelector('.wrappers');
    var newWrapper = document.createElement('div');
    newWrapper.classList.add('select');
    newNumber++;

    newWrapper.innerHTML = `
            <div style="margin: 10px">
            <input type="text" class="number" name="number" value="${newNumber}" readonly style="width: 20px; text-align: center">
            <input type="radio" id="wrapper1" name="wrapper" value="wrapper1">
            <label for="wrapper1">포장지 1</label>
            <input type="radio" id="wrapper2" name="wrapper" value="wrapper2">
            <label for="wrapper2">포장지 2</label>
            <input type="radio" id="none" name="wrapper" value="none">
            <label for="none">포장지 3</label>
            <input type="number" id="quantity" name="quantity" min="1" style="width: 40px">
            </div>`;

    wrappers.appendChild(newWrapper);
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
}

// '보기' 버튼을 클릭할 때마다 테이블 요소를 토글하는 함수
function toggleTable() {
    var table = document.querySelector('table'); // 테이블 요소 선택
    if (table.style.display === 'none' || table.style.display === '') {
        table.style.display = 'block'; // 테이블을 보이도록 설정
    } else {
        table.style.display = 'none'; // 테이블을 숨기도록 설정
    }
}

