// JS for set loading
var setGlobalLoading = function (isLoading) {
    $('#globalLoading')[isLoading ? 'addClass' : 'removeClass']('loading');
}

$(function () {
    // JS for custom select
    function setSelect(select, value, text) {
        $($(select).find('select')[0]).val(value).change();
        $(select).find('.select--default').html(text);

        $(select).find('.select--item').removeClass('active');
        $(select).find(`.select--item[data-value="${value}"]`).addClass('active');
    }

    $(".select").each(function () {
        const selected = $(this).find('option:selected')[0];
        const showDefault = `<p class="select--default">${selected.text}</p>`;
        let list = `<ul class="select--list">`;
        $(this).find('option').each((index, option) => {
            list += `<li class="select--item ${selected.value === option.value ? "active" : ""}" data-value="${option.value}" data-text="${option.text}">`
            list += `<span>${option.text}</span>`
            list += `<span class="icon"><i class="fas fa-angle-down"></i></span>`
            list += `</li>`;
        });
        list += `</ul>`;
        list += `<p class="icon"><i class="fas fa-angle-down"></i></p>`;
        $(this).append(showDefault + list);
    })

    $('.select').on('mouseenter', function () {
        $(this).find('.select--list')[0].classList.add('show');
    })

    $('.select').on('mouseleave', function () {
        $(this).find('.select--list')[0].classList.remove('show');
    })

    $('.select').each((index, select) => {
        if ($(select).find('.active').length) {
            const text = $(select).find('.active')[0].dataset.text;
            const value = $(select).find('.active')[0].dataset.value;

            setSelect(select, value, text);
        }
    })

    $('.select').on('click', '.select--item', function () {
        const select = $(this).parents('.select')[0];
        setSelect(select, this.dataset.value, this.dataset.text);

        $(select).find('.select--list')[0].classList.remove('show');
    })

    // JS for custom input has increase, decrease button
    $('.attachAdjust').each(function () {
        $(this).prepend('<span class="attachAdjust--button" data-value="minus"></span>');
        $(this).append('<span class="attachAdjust--button" data-value="plus"></span>');
    })
    $('.attachAdjust').on('click', ".attachAdjust--button", function () {
        const input = $(this).siblings("input")[0];
        switch ($(this).attr("data-value")) {
            case 'plus': {
                $(input).val(parseInt(input.value) + 1 > 99 ? 99 : parseInt(input.value) + 1).trigger('change');
                break;
            }
            case 'minus': {
                $(input).val(parseInt(input.value) - 1 < 1 ? 1 : parseInt(input.value) - 1).trigger('change');
                break;
            }
            default:
                break;
        }
    });

    // JS for dropdown
    if ($('.dropdown').length) {
        $('.dropdown > *:first-child').on('click', function () {
            const container = $(this).next()[0];
            $(container).slideToggle();
        })
    }

    // JS for checkbox
    $('input[type="checkbox"]:checked').each(function () {
        $(this).parents('.checkbox').addClass("checkbox__active");
    })
    $('.checkbox').on('change', "input[type='checkbox']", function () {
        const isCheck = this.checked;
        if (isCheck) {
            $(this).parents('.checkbox').addClass("checkbox__active");
        } else {
            $(this).parents('.checkbox').removeClass("checkbox__active");
        }
    })

    // JS for input radio
    $('input[type="radio"]').on("change", function () {
        const name = $(this).attr('name');
        $(`input[type="radio"][name=${name}]`).next().removeClass('active');
        $(this).next().addClass('active');
    })
    $('input[type="radio"]').each((index, item) => {
        if (item.checked) {
            $(item).next().addClass('active');
        }
    });

    // Js for cart footer is sticky
    if ($('.cart__list .cart-footer').length) {
        $(window).on('scroll', function () {
            const winBotPos = $(window).scrollTop() + $(window).height();
            const cartFooterBotPos = $('.cart-footer').offset().top + $('.cart-footer').height();
            if (winBotPos < cartFooterBotPos) {
                $('.cart-footer').addClass('cart-footer__float');
            } else {
                $('.cart-footer').removeClass('cart-footer__float');
            }
        });
    }

    // Js for modal
    $('.js-modal').on('click', function () {
        const id = this.dataset.id;
        $(`#${id}`).addClass('js-close');
    })
    $('.modal2-container').on('click', function (e) {
        if (e.target.classList.contains('js-close')) {
            $(this).removeClass('js-close');
        }
    })

    // Js for input date range
    if($('.jsDateRangePicker').length){
        $('.jsDateRangePicker').daterangepicker();
    }
});