document.addEventListener('DOMContentLoaded', function () {
    const searchInput = document.getElementById('search');
    let previousValue = searchInput.value;

    searchInput.addEventListener('input', function () {
        if (previousValue.trim() !== '' && searchInput.value.trim() === '') {
            window.location.href = window.location.pathname.split("/").slice(0, 2).join("/");
        }
        previousValue = searchInput.value;
    });
});


function confirmDelete(id, url) {
    let element = document.querySelector(`#row${id}`);
    console.log(element);
    if (!element) {
        console.error(`Element with ID #row${id} not found.`);
        return;
    }

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
        let element = document.querySelector(`#row${id}`);
        console.log(element);
        if (!element) {
            console.error(`Element with ID #row${id} not found.`);
            return;
        }

        if (result.isConfirmed) {
            // Utiliser htmx pour envoyer la requête DELETE
            htmx.ajax('DELETE', url, {
                target: `#` + "row" + id,
                swap: 'outerHTML'
            });
            document.body.addEventListener('htmx:afterRequest', (event) => {
                console.log(event)
                console.log(event.detail.pathInfo.requestPath)
                console.log(url)
                console.log(event.detail.pathInfo.requestPath === url )
                if (event.detail.requestConfig.verb.toUpperCase() === 'DELETE' && event.detail.successful && event.detail.pathInfo.requestPath === url) {
                    console.log(event.detail);
                    Swal.fire(
                        'Supprimé !',
                        'Le livre a été supprimé.',
                        'success'
                    );
                    element.remove();
                }
                else {
                    Swal.fire(
                        'Erreur !',
                        'Une erreur est survenue lors de la suppression.',
                        'error'
                    );
                }
            });
        }
    })
}