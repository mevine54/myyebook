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


function confirmDelete(event, id) {
    event.preventDefault();
    console.log(id)
    Swal.fire({
        title: 'Êtes-vous sûr ?',
        text: "Cette action est irréversible !",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oui, supprimer !',
        cancelButtonText: 'Annuler'
    }).then((result) => {
        if (result.isConfirmed) {
            // Utiliser htmx pour envoyer la requête DELETE
            htmx.ajax('DELETE', `LivreModification?id=${id}`, {
                target: `#row${id}`,
                swap: 'outerHTML'
            });
            Swal.fire(
                'Supprimé !',
                'Le livre a été supprimé.',
                'success'
            );
        }
    });
}