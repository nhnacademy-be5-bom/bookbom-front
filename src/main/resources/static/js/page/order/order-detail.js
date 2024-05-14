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

    document.getElementById('reviewModal').addEventListener('show.bs.modal', (event) => {
        const button = event.relatedTarget; // 클릭된 버튼
         // 버튼의 데이터(book-id)
        document.getElementById('bookIdField').value = button.getAttribute("data-book-id"); // 히든 필드에 book-id 설정
    });

    let saveButton = document.querySelector('.write-review');
    document.querySelector('.write-review').addEventListener('click', function () {
        let bookId = document.getElementById('bookIdField').value;
        let orderId = window.location.pathname.split('/').pop();
        let reviewType = document.getElementById('reviewType').value;

        console.log('bookId:', bookId);
        console.log('orderId:', orderId);
        console.log('reviewType:', reviewType);
        window.location.href = `/reviews?bookId=${bookId}&orderId=${orderId}&reviewType=${reviewType}`;
    });
});
