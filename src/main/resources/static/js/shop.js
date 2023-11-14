// Lấy các phần tử checkbox và badge
const checkboxes = document.querySelectorAll('.custom-control-input');
const badges = document.querySelectorAll('.badge');
const allPriceCheckbox = document.getElementById('price-all');
var currentPage = 0;

// Lắng nghe sự kiện khi checkbox thay đổi trạng thái
checkboxes.forEach((checkbox, index) => {
    checkbox.addEventListener('change', () => {
        if (checkbox.checked) {
            const selectedValue = checkbox.value;
            const [minValue, maxValue] = selectedValue.split('-');
            const min = parseFloat(minValue);
            const max = parseFloat(maxValue);
            console.log(`Min value: ${min}`);
            console.log(`Max value: ${max}`);
            // Hủy chọn các checkbox khác nếu checkbox hiện tại được chọn
            checkboxes.forEach((cb, i) => {
                if (cb !== checkbox) {
                    cb.checked = false;
                }
            });
            // // Cập nhật giá trị tổng giá lên badge của "All Price"
            // document.getElementById('price-all-badge').textContent = badges[index].textContent;
            currentPage++;
            $.ajax({
                url: 'api/search_shop',
                type: 'GET',
                data: {maxprice: max, minPrice: min },
                success: function (data) {
                    renderShop(data);
                },
                error: function () {
                    console.error('API request failed');
                }
            });

        }
    });
});

// Lắng nghe sự kiện khi checkbox All Price thay đổi trạng thái
allPriceCheckbox.addEventListener('change', () => {
    checkboxes.forEach(checkbox => {
        checkbox.checked = allPriceCheckbox.checked;
    });
});


function renderShop(data) {
    console.log(data);
    var shopList = $('.shoplist');
    shopList.empty(); // Làm rỗng danh sách phòng hiện tại
    if (data.length === 0) {
        // Nếu dữ liệu rỗng, hiển thị thông báo "Không có sản phẩm"
        shopList.html("<h2 style='text-align: center'>There are no boarding houses or rooms.</h2>");
    } else {
    // Loop through the listrooms data and render each room
    data.forEach(function (c) {
        var shopItem = `
            <div class=" col-lg-4 col-md-6 col-sm-6 pb-1">
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
                                <a class="h6 text-decoration-none text-truncate" href="">${c.rooms_title}</a>
                                <div class="d-flex align-items-center justify-content-center mt-2">
                                    <h5>${c.rooms_price}</h5><h6 class="text-muted ml-2"><del>$123.00</del></h6>
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
        shopList.append(shopItem);
    });}
}
