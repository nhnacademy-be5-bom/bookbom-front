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

    window.location.href = `/users/orders?date_from=${dateFrom}&date_to=${dateTo}`;
}
