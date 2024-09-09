document.addEventListener('DOMContentLoaded', function () {
    const deleteButton = document.querySelector('.delete-button');

    deleteButton.addEventListener('click', function () {
        const diaryId = deleteButton.getAttribute('data-id');
        const url = `/MyDiary/delete/${diaryId}`;

        const confirmation = confirm('삭제하시겠습니까?');
        if (!confirmation) {
            return; // 사용자가 취소한 경우 함수 종료
        }

        // Fetch API로 DELETE 요청
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                window.location.href = '/MyDiary/list'; // 삭제 후 목록으로 리다이렉트
            } else {
                alert('Failed to delete the diary entry.');
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    });
});
