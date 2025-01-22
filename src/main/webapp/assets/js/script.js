document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('search');
    let previousValue = searchInput.value;

    searchInput.addEventListener('input', function() {
        if (previousValue.trim() !== '' && searchInput.value.trim() === '') {
            window.location.href = '/myyebook_war/accueil';
        }
        previousValue = searchInput.value;
    });
});