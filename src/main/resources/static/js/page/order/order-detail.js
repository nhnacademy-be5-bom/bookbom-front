document.addEventListener("DOMContentLoaded", function () {
    document.querySelector('.confirm-delete').addEventListener('click', function () {
        const orderId = window.location.pathname.split('/').pop();
        console.log('orderId:', orderId)
        fetch(`/orders/${orderId}`, {
            method: 'DELETE'
        }).then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('주문 취소 요청에서 에러가 발생했습니다.');
        }).then(response => {
            console.log('Success:', response);
            $('#orderCancel').modal('hide');
            window.location.reload();
        }).catch(error => {
            console.error('Error:', error);
        });
    });

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
});
