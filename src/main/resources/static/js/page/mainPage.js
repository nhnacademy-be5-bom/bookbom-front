document.addEventListener('DOMContentLoaded', function () {
    // 장바구니 버튼 클릭
    document.querySelectorAll('.btn-cart').forEach(button => {
        button.addEventListener('click', function () {
            event.preventDefault();
            const quantity = 1
            // 책 ID를 가져옴
            const bookId = this.dataset.bookId;

            // 책 정보를 가져옴
            const card = this.closest('.card');
            const thumbnail = card.querySelector('.book-thumbnail').src;
            const title = card.querySelector('.card-title').textContent;
            const price = parseInt(card.querySelector('.original-price').textContent.replace(',', '').replace('원', ''));
            const discountPrice = parseInt(card.querySelector('.book-discount-cost').textContent.replace(',', '').replace('원', ''));

            // 요청 데이터 생성
            const requestData = [
                {
                    bookId: parseInt(bookId),
                    thumbnail: thumbnail,
                    title: title,
                    price: price,
                    discountPrice: discountPrice,
                    quantity: quantity
                }
            ];

            // AJAX를 사용하여 POST 요청 전송
            fetch('/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 에러가 발생했습니다.');
                    }
                    return response.json();
                })
                .then(data => {
                    let myModal = new bootstrap.Modal(document.getElementById('successModal'), {
                        keyboard: false
                    });
                    myModal.show();

                    document.getElementById('goToCart').addEventListener('click', () => {
                        window.location.href = '/cart';
                    });

                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });
    });

    const bannerSwiper = new Swiper('.main-swiper', {
        loop: true,
        autoplay: {
            delay: 15000,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });

    // 인기 상품 Swiper 설정
    const bestSwiper = new Swiper('.products-carousel', {
        slidesPerView: 'auto',
        spaceBetween: 30,
        loop: true,
        autoplay: {
            delay: 3000,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });

    // testimonial Swiper 설정
    const testimonialSwiper = new Swiper('.testimonial-swiper', {
        loop: true,
        autoplay: {
            delay: 10000,
        },
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });
});
