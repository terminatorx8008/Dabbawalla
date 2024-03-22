const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

// Assuming sign_up_btn is a reference to the sign-up button
sign_up_btn.addEventListener("click", () => {
    // Assuming container is a reference to the container element
    container.classList.add("sign-up-mode");
});


sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});

function filterMenu(category) {
    const items = document.querySelectorAll('.menu-item');
    items.forEach(item => {
        if (category === 'all' || item.classList.contains(category)) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function filterMenuByDay(day) {
    const items = document.querySelectorAll('.menu-item');
    items.forEach(item => {
        const menuItemId = item.getAttribute('id');
        const menuDayElement = item.querySelector('#menuDay');

        if (menuDayElement && menuDayElement.textContent.toLowerCase().includes(day.toLowerCase())) {
            item.style.display = 'block';
        } else {
            item.style.display = 'none';
        }
    });
}

function closeEditPanel() {
    // Fetch menu information based on the menuId using AJAX or any other method
    document.getElementById('editPanel').style.display = 'none';
    document.getElementById('addPanel').style.display = 'none';
}


function openEditPanel(button) {
    var menu = {
        menuId: button.getAttribute('data-menuId'),
        menuName: button.getAttribute('data-menuName'),
        menuPrice: button.getAttribute('data-menuPrice'),
        menuTiming: button.getAttribute('data-menuTiming'),
        menuDay: button.getAttribute('data-menuDay'),
        menuImage: button.getAttribute('data-menuImage'),
        menuDescription: button.getAttribute('data-menuDescription')
    };
    // Set values in the edit form]
    document.getElementById('itemName').value = menu.menuName;
    document.getElementById('itemPrice').value = menu.menuPrice;
    document.getElementById('itemTiming').value = menu.menuTiming;
    document.getElementById('itemDay').value = menu.menuDay;
    document.getElementById('itemDescription').value = menu.menuDescription;
    document.getElementById('itemId').value = menu.menuId;

    // Show the edit panel
    document.getElementById('editPanel').style.display = 'block';
}

function openAddPanel() {
    // Fetch menu information based on the menuId using AJAX or any other method
    document.getElementById('addPanel').style.display = 'block';
}

function closeAlert() {
    var alert = document.querySelector('.alert');
    alert.style.display = 'none';
}

function checkImageSize() {
    // Get the input element
    var inputElement = document.getElementById("imageInput");

    // Check if any file is selected
    if (inputElement.files.length > 0) {
        // Get the first file from the selected files
        var file = inputElement.files[0];
        console.log(file.size);
        // Check the size of the file
        if (file.size > 2 * 1024 * 1024) {
            alert("Image size is greater than or equal to 5 bytes.");
        }
    } else {
        alert("No file selected.");
    }
}

// send a request to the server to add the menu to order
function addToCart(button) {
    // Get the menuId from the button
    var menuId = button.getAttribute('menuId');
    console.log(menuId);
    // Send a request to the server to add the menu to order
    fetch(`/order/request-tiffin/${menuId}`)
        .then(res => {
            if (!res.ok) {
                throw new Error(`HTTP error! Status: ${res.status}`);
            }
            return res.text();
        })
        .then(data => {
            // Check if the response contains the expected string
            if (data.toLowerCase().includes('order placed')) {
                alert('Order placed successfully.');
            } else {
                alert('Menu not added to order.');
            }
        })
        .catch(error => {
            console.error('Fetch error:', error);
            alert('Error communicating with the server.');
        });
}

function showFileInput() {
    document.getElementById('fileInput').click();
}

function previewImage(input) {
    const profileImage = document.getElementById('profileImage');
    const file = input.files[0];

    if (file) {
        const reader = new FileReader();

        reader.onload = function (e) {
            profileImage.innerHTML = `<img src="${e.target.result}" alt="Profile Image">`;
        };

        reader.readAsDataURL(file);
    }
}

function openMessdetails(messId) {
    window.location.href = `/user/mess-details/${messId}`;
}

function showSearchOverlay() {
    document.getElementById('searchOverlay').style.left = '50px';
}

function closeSearchOverlay() {
    document.getElementById('searchOverlay').style.left = '-500px';
}

function searchmesses() {
    console.log("searching");
    let searchInput = document.getElementById("searchInput").value;
    let searchResults = document.getElementById("searchResults");
    console.log(searchInput);
    if (searchInput == "") {
        searchResults.innerHTML = "";
    } else {
        fetch(`/search/${searchInput}`)
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                console.log(data);
                searchResults.innerHTML = "";
                const template = document.getElementById("searchResultTemplate").content;
                data.forEach((item) => {
                    const clone = document.importNode(template, true);
                    clone.querySelector(".search-result-title").textContent = item.messName;
                    clone.querySelector(".search-result-description").textContent = item.messDescription;
                    clone.querySelector(".search-result-location").textContent = item.messAddress;
                    clone.querySelector("a").setAttribute("href", `/user/mess-details/${item.messId}`);
                    searchResults.appendChild(clone);
                });
            });
    }
}

function handleBackspace(event) {
    const input = document.getElementById("searchInput");
    if (event.key === "Backspace" && input.value.length === 0) {
        let searchResults = document.getElementById("searchResults");
        searchResults.innerHTML = "";
    }
}

const searchInput = document.getElementById("searchInput");
searchInput.addEventListener("keydown", handleBackspace);

$(document).ready(function () {
    function slideWidth() {
        return $('.review-box').outerWidth();
    }

    function resetSlider() {
        $('.review-carousel').css('transform', 'translateX(-' + slideWidth() * ($('.review-box').length - 1) + 'px)');
        $('.review-box').first().addClass('active');
    }

    resetSlider();

    function nextSlide() {
        $('.review-box.active').removeClass('active').next().addClass('active');
        $('.review-carousel').css('transform', 'translateX(-' + slideWidth() * ($('.review-box.active').index()) + 'px)');
    }

    function prevSlide() {
        $('.review-box.active').removeClass('active').prev().addClass('active');
        $('.review-carousel').css('transform', 'translateX(-' + slideWidth() * ($('.review-box.active').index()) + 'px)');
    }

    $('.next').click(function () {
        if ($('.review-box.active').is(':last-child')) {
            resetSlider();
        } else {
            nextSlide();
        }
    });

    $('.prev').click(function () {
        if ($('.review-box.active').is(':first-child')) {
            $('.review-box').last().addClass('active');
            $('.review-carousel').css('transform', 'translateX(-' + slideWidth() * ($('.review-box').length - 1) + 'px)');
        } else {
            prevSlide();
        }
    });

    setInterval(function () {
        if ($('.review-box.active').is(':last-child')) {
            resetSlider();
        } else {
            nextSlide();
        }
    }, "5000");
});

function addToFav(element) {
    let messId = element.getAttribute('messId');
    console.log(messId);
    fetch(`/user/add-to-fav/${messId}`)
        .then((res) => {
            return res.json();
        })
        .then((data) => {
            toggleStyle(element);
            // window.location.reload();
        })
        .catch((error) => {
            alert(error);
        });
}

function toggleStyle(element) {
    element.classList.toggle('favorited');
}
function requestCancellation(element){
    let messId = element.getAttribute('messId');
    window.location.href = `/user/request-cancellation/${messId}`;
}