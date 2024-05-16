function selected(e) {
    var selectedValue = e.value;
    var generalContainer = document.querySelector('.generalCouponContainer');
    var bookContainer = document.querySelector('.bookCouponContainer');
    var categoryContainer = document.querySelector('.categoryCouponContainer');

    if (selectedValue === 'general') {
        generalContainer.style.display = 'block';
        bookContainer.style.display = 'none';
        categoryContainer.style.display = 'none';
    } else if (selectedValue === 'book') {
        generalContainer.style.display = 'none';
        bookContainer.style.display = 'block';
        categoryContainer.style.display = 'none';
    } else if (selectedValue === 'category') {
        generalContainer.style.display = 'none';
        bookContainer.style.display = 'none';
        categoryContainer.style.display = 'block';
    }
}

function addCoupon(event) {
    var registBtn = event.target;
    var container = registBtn.parentNode.parentNode;
    var couponName = container.querySelector('.couponName').value;
    var couponPolicyId = container.querySelector('.couponPolicyId').value;
    var couponPeriod = container.querySelector('.couponPeriod').value;

    if (couponName.trim() === '') {
        alert('쿠폰 이름을 입력하세요.');
        return;
    }

    if (couponPolicyId.trim() === '') {
        alert('쿠폰 정책 번호를 입력하세요.');
        return;
    }

    if (couponPeriod.trim() === '') {
        alert('유효 기간을 입력하세요.');
        return;
    }

    var currentDate = new Date();
    if (new Date(couponPeriod) < currentDate) {
        alert('유효 기간은 현재 날짜보다 빨라야 합니다.');
        return;
    }

    var requestBody = JSON.stringify({
        name: couponName,
        couponPolicyId: couponPolicyId,
        period: couponPeriod
    });

    var type = "general";
    var bookId = '';
    if (container.classList.contains('bookCouponContainer')) {
        bookId = container.querySelector('.bookId').value;
        if (bookId.trim() === '') {
            alert('도서 Id를 입력하세요.');
            return;
        } else {
            requestBody = JSON.stringify({
                name: couponName,
                couponPolicyId: couponPolicyId,
                period: couponPeriod,
                bookId: bookId
            });
            type = "book";
        }
    }

    var categoryId = '';
    if (container.classList.contains('categoryCouponContainer')) {
        categoryId = container.querySelector('.categoryId').value;
        if (categoryId.trim() === '') {
            alert('카테고리 Id를 입력하세요.');
            return;
        } else {
            requestBody = JSON.stringify({
                name: couponName,
                couponPolicyId: couponPolicyId,
                period: couponPeriod,
                categoryId: categoryId
            });
            type = "category";
        }
    }

    console.log(type);
    fetch(`/admin/couponRegister/${type}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: requestBody
    }).then(response => {
        if (response.ok) {
            alert("등록이 완료되었습니다.");
            location.href = "/admin/coupons/general";
        } else {
            console.error("등록 중 오류가 발생했습니다.");
        }
    }).catch(error => {
        console.error("요청을 보내는 중 오류가 발생했습니다:", error);
    });
}
