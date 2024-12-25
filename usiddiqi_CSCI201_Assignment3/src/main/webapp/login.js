document.addEventListener('DOMContentLoaded', () => {
    function storage() {
        const registerLink = document.querySelector('.topnav a[href="register.html"]');
        const searchInput = document.querySelector('.info input[type="text"]');

        //localStorage.setItem('username', 'Umby');
		
		localStorage.clear(); 
        
        if (localStorage.getItem('username')) {
            if (registerLink) {
                registerLink.textContent = 'Profile';
                registerLink.href = 'profile.html'; 
            }
        } 

      
    }

    // Call the storage function to apply the changes
    storage();
});