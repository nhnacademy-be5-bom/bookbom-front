document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.getElementById("search-form");
    searchForm.addEventListener("submit", function (event) {
        let input = searchForm.querySelector('input[name="keyword"]');

        if (input.value.trim().length === 0) {
            event.preventDefault();
            alert("검색할 도서를 입력하세요.");
        }
    });
});

window.onload = function () {
    const categoryList = document.querySelectorAll(".categorypop");

    categoryList.forEach(function (category) {
        category.addEventListener("mouseover", updateChildCategory);

        category.addEventListener("click", function (e) {
            document.location.href = "/category/" + this.id;
        });

        setDefaultChildCategory(category);
    })

    observer.observe(document, {attributes: false, childList: true, characterData: false, subtree: true});
};

let popoverTriggerList = [].slice.call(
    document.querySelectorAll('[data-bs-toggle="popover"]'));


let popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
    return new bootstrap.Popover(popoverTriggerEl, {
        placement: 'right',
        content: "",
        delay: {show: 0, hide: 75},
        trigger: 'hover',
        html: true
    });
})

async function updateChildCategory(e) {
    let popoverElement = e.target;
    let popoverInstance = bootstrap.Popover.getInstance(popoverElement);

    if (popoverInstance._config.content.length === 0) {
        const response = await fetch(
            GATEWAY_URL + "/shop/categories" + this.id,
            {
                method: "GET"
            });

        const childList = await response.json();

        if (childList.result.length !== 0) {

            for (const child of childList.result) {
                const content1 = "<p><a href='/category/";
                const content2 = "'>";
                const content3 = "</a></p>"

                popoverInstance._config.content += content1 + child.id + content2 + child.name + content3;
            }
        }
    }
}

async function setDefaultChildCategory(categoryElement) {
    let popoverInstance = bootstrap.Popover.getInstance(categoryElement);

    if (popoverInstance._config.content.length === 0) {
        const response = await fetch(
            GATEWAY_URL + "/shop/categories" + categoryElement.id,
            {
                method: "GET"
            });

        const childList = await response.json();

        if (childList.result.length !== 0) {

            for (const child of childList.result) {
                const content1 = "<p><a href='/category/";
                const content2 = "'>";
                const content3 = "</a></p>"

                popoverInstance._config.content += content1 + child.id + content2 + child.name + content3;
            }
        }
    }
}

const observer = new MutationObserver(function (mutations) {
    mutations.forEach(function (mutation) {
        mutation.addedNodes.forEach(function (added_node) {
            if (added_node.id.includes('popover')) {
                let popElem = document.querySelector('[aria-describedby="' + added_node.id + '"]');

                added_node.addEventListener('mouseenter', function () {
                    const popover_instance = bootstrap.Popover.getInstance(popElem);
                    const hide_func = popover_instance.hide;
                    popover_instance.hide = fn_noop;
                    this.addEventListener('mouseleave', (ev) => {
                        popover_instance.hide = hide_func;
                        popover_instance.hide();
                    }, {once: true});
                });
            }
        });
    });
});

function fn_noop() {
};
