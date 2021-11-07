$(function () {
    function setSelect(select, value, text) {
        $($(select).find('select')[0]).val(value).change();
        $(select).find('.select--default').html(text);

        $(select).find('.select--item').removeClass('active');
        $(select).find(`.select--item[data-value="${value}"]`).addClass('active');
    }

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
})