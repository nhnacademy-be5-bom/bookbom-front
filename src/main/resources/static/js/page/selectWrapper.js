// '다음' 버튼을 클릭할 때 validateRadioButtonSelection 함수를 호출합니다.
function selectWrapperSubmit() {
    var isValid = validateRadioButtonSelection();
    console.log("버튼 클릭");
    if (!isValid) {
        // 모든 라디오 버튼이 선택되었으면 다음 이벤트를 진행합니다
        alert("포장지 선택을 진행해주세요");
    } else {
        alert("선택한 포장지가 유효합니다!");
    }

}

function validateRadioButtonSelection() {
    // 모든 라디오 버튼을 가져옵니다.
    var radioButtons = document.querySelectorAll('input[type="radio"]');

    // 각 라디오 버튼에 대해 확인합니다.
    for (var i = 0; i < radioButtons.length; i++) {
        var radioButton = radioButtons[i];

        var groupNames = new Set(); // 중복을 허용하지 않는 name을 저장할 Set

        // 각 라디오 버튼에 대해 확인합니다.
        for (var i = 0; i < radioButtons.length; i++) {
            var radioButton = radioButtons[i];

            // 현재 라디오 버튼의 name 속성값을 가져와서 Set에 추가합니다.
            var groupName = radioButton.getAttribute('name');
            groupNames.add(groupName);
        }
    }
    for (var groupName of groupNames.values()) {
        // 같은 name을 가진 라디오 버튼들을 가져옵니다.
        var group = document.querySelectorAll('input[type="radio"][name="' + groupName + '"]');
        // 해당 그룹에서 체크된 라디오 버튼의 개수를 세어봅니다.
        var isChecked = 0;
        for (var j = 0; j < group.length; j++) {
            if (group[j].checked) {
                isChecked += 1;
                break;
            }
        }
        if (isChecked === 0) {
            return false;
        }
    }
    return true;

}

// // 모든 라디오 버튼에 대해 그룹별로 업데이트 함수 호출
// var radioButtons = document.querySelectorAll('input[type="radio"]');
//
// // 각 라디오 버튼에 대해 확인합니다.
// for (var i = 0; i < radioButtons.length; i++) {
//     var radioButton = radioButtons[i];
//
//     var groupNames = new Set(); // 중복을 허용하지 않는 name을 저장할 Set
//
//     // 각 라디오 버튼에 대해 확인합니다.
//     for (var j = 0; j < radioButtons.length; j++) {
//         var radioButton = radioButtons[j]; // 여기서 변수명을 바꿔줍니다.
//
//         // 현재 라디오 버튼의 name 속성값을 가져와서 Set에 추가합니다.
//         var groupName = radioButton.getAttribute('name');
//         groupNames.add(groupName);
//     }
// }
// for (var groupName of groupNames.values()) {
//     // 라디오 버튼이 변경될 때마다 업데이트 함수 호출
//     $('input[name="' + groupName + '"]').on('change', updateWrapCountAndCost);
// }

function updateWrapCountAndCost() {

    // wrapBookCount 및 totalWrapCost 업데이트를 위해 필요한 변수 초기화
    var totalQuantity = 0;
    var totalCost = 0;

    // 각 라디오 버튼에 대해 확인합니다.
    // 체크된 라디오 버튼에 대해 확인합니다.
    $('input[type="radio"]:checked').each(function () {
        // 각 체크된 라디오 버튼의 id와 value 값을 가져와서 계산합니다.
        var id = parseFloat($(this).attr('id'));
        var value = parseFloat($(this).val());
        if (id !== 0) {
            // totalQuantity를 id 값으로부터 계산합니다.
            totalQuantity += value;
        }
        // totalCost를 id와 value를 곱한 값을 더하여 계산합니다.
        totalCost += id * value;
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



