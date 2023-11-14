var currentPage = 0;
function loadMore() {
    var spinner = document.querySelector('.spinner');
    var xemThemButton = document.querySelector('.xem-them-button');

    // Ẩn nút "Xem thêm" và hiển thị spinner
    xemThemButton.style.display = 'none';
    spinner.style.display = 'block';

    // Đợi 5 giây, sau đó ẩn spinner và hiển thị lại nút "Xem thêm"
    setTimeout(function () {
        spinner.style.display = 'none';
        xemThemButton.style.display = 'block';
        currentPage++;
        $.ajax({
            url: '/api/listrooms', // Correct the URL
            type: 'GET',
            data: { page: currentPage, size: 4 },
            success: function (data) {
                // Call renderRoom with the data directly
                renderRoom(data);
            },
            error: function () {
                console.error('API request failed');
            }
        });
    }, 3000);
}

function renderRoom(data) {
    var roomList = $('.room-list');
    // roomList.empty(); // Làm rỗng danh sách phòng hiện tại

    // Loop through the listrooms data and render each room
    data.forEach(function(room) {
        var roomItem = `
            <div class="col-lg-3 col-md-4 col-sm-6 pb-1">
                <div class="product-item bg-light mb-4">
                    <div class="product-img position-relative overflow-hidden">
                        <img class="img-fluid w-100" src="img/product-1.jpg" alt="">
                        <div class="product-action">
                            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-shopping-cart"></i></a>
                            <a class="btn btn-outline-dark btn-square" href=""><i class="far fa-heart"></i></a>
                            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-sync-alt"></i></a>
                            <a class="btn btn-outline-dark btn-square" href=""><i class="fa fa-search"></i></a>
                        </div>
                    </div>
                    <div class="text-center py-4">
                        <a class="h6 text-decoration-none text-truncate" href="#"><span>${room.rooms_title}</span></a>
                        <div class="d-flex align-items-center justify-content-center mt-2">
                            <!-- Include the room price and discount here -->
                        </div>
                        <div class="d-flex align-items-center justify-content-center mb-1">
                            <small class="fa fa-star text-primary mr-1"></small>
                            <small class="fa fa-star text-primary mr-1"></small>
                            <small class="fa fa-star text-primary mr-1"></small>
                            <small class="fa fa-star text-primary mr-1"></small>
                            <small class="fa fa-star text-primary mr-1"></small>
                            <small>(99)</small>
                        </div>
                    </div>
                </div>
            </div>
        `;
        roomList.append(roomItem);
    });
}
