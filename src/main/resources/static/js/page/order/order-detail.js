function openReviewModal(bookId) {
    document.getElementById("bookIdField").value = bookId;

    const myModal = new bootstrap.Modal(document.getElementById('reviewModal'));
    myModal.show();
}

document.querySelectorAll('.review').forEach(item => {
    item.addEventListener('click', event => {
        const bookId = item.getAttribute('data-book-id');
        let orderId = window.location.pathname.split('/').pop();
        const url = `/reviews/exists-check?bookId=${bookId}&orderId=${orderId}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    alert('리뷰를 작성할 수 없습니다. 관리자에게 문의 주세요.');
                    return;
                }
                return response.json();
            })
            .then(data => {
                console.log(data)
                if (data.header.isSuccessful === false) {
                    alert('리뷰를 작성할 수 없습니다. 관리자에게 문의 주세요.');
                    return;
                }
                if (data.result.exists === true) {
                    alert('이미 리뷰를 작성하셨습니다.');
                    return;
                }
                openReviewModal(bookId);
            })
            .catch(error => {
                alert('리뷰를 작성할 수 없습니다. 관리자에게 문의 주세요.');
                console.error('Error:', error);
            });
    });
});

document.querySelector('.write-review').addEventListener('click', function () {
    let bookId = document.getElementById('bookIdField').value;
    let orderId = window.location.pathname.split('/').pop();
    let reviewType = document.getElementById('reviewType').value;
    window.location.href = `/reviews?bookId=${bookId}&orderId=${orderId}&reviewType=${reviewType}`;
});
// document.addEventListener("DOMContentLoaded", function () {
//     document.querySelector('.confirm-delete').addEventListener('click', function () {
//         const orderId = window.location.pathname.split('/').pop();
//         console.log('orderId:', orderId)
//
//     });
// });

// const orderIdValue = document.getElementById("orderId").value;
// const orderId = parseInt(orderIdValue);
document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.confirm-delete').addEventListener('click', function () {
        // 팝업 창으로 페이지 열기
        // orderId 가져오기
        const orderId = document.getElementById("orderId").value;
        // 팝업 창으로 페이지 열기
        window.open("/order/cancel?id=" + orderId, '_blank', 'width=600,height=400,toolbar=0,location=0,menubar=0,status=0,scrollbars=1,resizable=1');
    });
});
