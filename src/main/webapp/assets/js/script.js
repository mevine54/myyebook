document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('search');
    let previousValue = searchInput.value;

    searchInput.addEventListener('input', function() {
        if (previousValue.trim() !== '' && searchInput.value.trim() === '') {
            window.location.href = window.location.pathname.split("/").slice(0,2).join("/");
        }
        previousValue = searchInput.value;
    });
});