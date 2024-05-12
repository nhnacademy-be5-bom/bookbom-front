function printLocalStorageItems() {
    var couponId = localStorage.getItem("couponId");
    var couponName =localStorage.getItem("couponName");

    var couponIdBox = document.getElementById("couponIdBox");
    var couponNameBox = document.getElementById("couponNameBox");
    couponIdBox.innerText = couponId;
    couponNameBox.innerText = couponName;
}

function createDiv() {
    var memberEmail = document.getElementById("memberSearchBox").value;
    fetch('/users/check-email?email=' + encodeURIComponent(memberEmail))
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (!data.result.canUse) {
                var newDiv = document.createElement("div");
                var newLabel = document.createElement("label");
                var newText = document.createTextNode(memberEmail);
                newLabel.appendChild(newText);
                newLabel.setAttribute("class", "memberEmail");
                var newButton = document.createElement("button");
                newText = document.createTextNode("취소");
                newButton.appendChild(newText);
                newButton.addEventListener("click", function (e){
                    //취소 버튼 클릭시
                    e.target.parentNode.remove();
                })
                newDiv.appendChild(newLabel);
                newDiv.appendChild(newButton);

                var container = document.getElementById("selectedMemberContainer");
                container.appendChild(newDiv);
            }
            else{
                alert("등록되지 않은 사용자입니다.");
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("에러가 발생했습니다. 관리자에게 문의 주세요.")
        });
}

function issue(){
    //만료일이 현재 날짜보다 늦은지 확인
    var expireDate = document.getElementById("expireDate").value;
    var currentDate = new Date();
    if (new Date(expireDate) < currentDate) {
        alert('쿠폰 만료일은 발급 날짜 이후로 입력해주세요.');
        return;
    }
    if(expireDate == ""){
        alert('쿠폰 만료일을 입력해주세요.');
        return;
    }

    //쿠폰 아이디 localstorage에서 가져오기 + 삭제
    var couponId = localStorage.getItem("couponId");

    //추가된 div의 id들 가져와서 list에 저장
    var emailList = [];
    var memberEmailLabel = document.querySelectorAll('.memberEmail');
    memberEmailLabel.forEach(function (email) {
        emailList.push(email.innerHTML);
    });
    console.log(emailList);

    //api에 발급 요청
    fetch(`/admin/coupons/issue`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            userEmailList : emailList,
            couponId : couponId,
            expireDate : expireDate
        })
    }).then(response => {
        if (response.ok) {
            alert("쿠폰 발급이 완료되었습니다.");
            location.href = "/admin/coupons/general";
        } else {
            console.error("쿠폰 발급 중 오류가 발생했습니다.");
        }
    }).catch(error => {
        console.error("요청을 보내는 중 오류가 발생했습니다:", error);
    });
}
