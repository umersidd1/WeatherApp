

//let map; 

document.addEventListener('DOMContentLoaded', () => {
    
    const cityRadio = document.getElementById('city');
    const latitudeRadio = document.getElementById('latitude');
	const searchInput = document.querySelector('.mainbody input[type="text"]');

    //MAY DELETE


    const registerLink = document.querySelector('.topnav a[href="register.html"]');

    const loginLink = document.querySelector('.topnav a[href="login.html"]');

	document.getElementById('button').addEventListener('click', function(event) {
	   
	    
	    // Show the weather table
	    document.getElementById('weatherTable').style.display = 'table';
	});
  


    if(localStorage.getItem('username')){
		
		
        if(registerLink){
            registerLink.textContent = 'Sign Out';
            registerLink.href = 'index.html';
			registerLink.onclick = function(event) {
			        localStorage.clear(); // This clears all localStorage data
			};
        }
        if(loginLink){
            loginLink.textContent = 'Profile';
            loginLink.href = 'profile.html';
        }
    }
    


    // Get reference to the input field and the magnifying glass image
    //const searchInput = document.querySelector('.mainbody input[type="text"]');
    const magnifyingImage = document.querySelector('.magnifying img');
    //const magnifyingLink = document.getElementById('magnifying-link');
    const magnifying = document.querySelector('.magnifying');

    // Add event listener to latitude radio button
    latitudeRadio.addEventListener('change', () => {
        if (latitudeRadio.checked) {
            // Change input placeholder to indicate lat/long
            searchInput.placeholder = "Latitude";
            searchInput.style.width = '13.8%';
            searchInput.style.marginLeft = "-11.8rem";
			

			
			
			

            // Change the magnifying glass image to a different image
            magnifyingImage.src = './Assignment 3 Images/MapIcon.png';
            magnifyingImage.alt = 'Google Map';
            //magnifyingLink.href = 'https://www.google.com/maps'; // Set the link to Google Maps
            magnifyingImage.style.height = '20px'; 
            magnifyingImage.style.width = '20px';
            magnifyingImage.style.marginTop = '7px';
            magnifyingImage.style.marginLeft = '17px';
            magnifying.style.backgroundColor = '#555555';
            magnifying.style.height = '36px';
            magnifying.style.width = '50px';
            magnifying.style.marginTop = '290px';

            // Add a second search bar for longitude when Lat/Long is selected
            const mainBody = document.querySelector('.mainbody');
            if (!document.getElementById('longitude-input')) { // Prevent adding multiple inputs
                // Create a new input element for Longitude
                const longitudeInput = document.createElement('input');
                longitudeInput.type = 'text';
                longitudeInput.id = 'longitude-input';
                longitudeInput.name = 'longitude';
                longitudeInput.placeholder = 'Longitude';
                longitudeInput.classList.add('search-bar');  // Add a class for consistent styling
                longitudeInput.style.width = '15%';
                longitudeInput.style.height = '30px';
                longitudeInput.style.marginTop = '-8.475rem';
                longitudeInput.style.marginLeft = '14.7rem';

                // Append the new input field below the current search input
                mainBody.appendChild(longitudeInput);
            }
        }
    });

    // Add event listener to city radio button to reset and remove the second search bar
    
});

function  display() {
    const searchInput = document.querySelector('.mainbody input[type="text"]');
    const topnav = document.querySelector('.topnav');
    const magnifyingGlass = document.querySelector('.magnifying img');
    const cityRadio = document.getElementById('city');
    const latitudeRadio = document.getElementById('latitude');
    const cityLabel = document.querySelector('label[for="city"]');
    const latitudeLabel = document.querySelector('label[for="latitude"]');
    const radioButtons = document.querySelector('.radio-buttons');
    const button = document.getElementById('button');
    const logoImg = document.querySelector('.mainbody img');

	if (latitudeRadio.checked) {
	    // Create a container for the images
	    const imageContainer = document.createElement('div');
	    imageContainer.classList.add('image-container');

	    // Create the first row container
	    const firstRow = document.createElement('div');
	    firstRow.classList.add('image-row');

	    // Create the second row container
	    const secondRow = document.createElement('div');
	    secondRow.classList.add('image-row');

	    // Function to create an image with a paragraph below it
	    function createImageWithParagraph(src, alt, id) {
	        const container = document.createElement('div');
	        container.style.textAlign = 'center'; // Center align the content

	        const img = document.createElement('img');
	        img.src = src;
	        img.alt = alt;
	        img.style.width = '3rem';
	        img.style.height = '3rem';

	        const paragraph = document.createElement('p');
	        paragraph.textContent = '1';
	        paragraph.style.margin = '0'; // Remove default margin
			paragraph.style.color = 'white'; // Make text visible on dark background
			paragraph.style.fontSize = '1rem';

            if (id){
                paragraph.id = id;
            }

	        container.appendChild(img);
	        container.appendChild(paragraph);

	        // return {container, paragraph};
            return container; 
	    }

	    // Create the image elements with paragraphs
	    const earth = createImageWithParagraph('./Assignment 3 Images/planet-earth.png', 'Earth', 'earth');
	    const snowflake = createImageWithParagraph('./Assignment 3 Images/snowflake.png', 'Snowflake', 'snow');
	    const sun = createImageWithParagraph('./Assignment 3 Images/sun.png', 'Sun',  'sun');
	    const wind = createImageWithParagraph('./Assignment 3 Images/wind.png', 'Wind', 'wind');
	    const water = createImageWithParagraph('./Assignment 3 Images/drop.png', 'Water Droplet', 'water');
	    const location = createImageWithParagraph('./Assignment 3 Images/LocationIcon.png', 'Location', 'loc');
	    const thermometer = createImageWithParagraph('./Assignment 3 Images/thermometer.png', 'Thermometer', 'therm');
	    const sunrise = createImageWithParagraph('./Assignment 3 Images/sun.png', 'Sunrise', 'sunrise');

      

	    // Append the images to the first row
	    firstRow.appendChild(earth);
	    firstRow.appendChild(snowflake);
	    firstRow.appendChild(sun);
	    firstRow.appendChild(wind);

	    // Append the images to the second row
	    secondRow.appendChild(water);
	    secondRow.appendChild(location);
	    secondRow.appendChild(thermometer);
	    secondRow.appendChild(sunrise);

	    // Append the rows to the container
	    imageContainer.appendChild(firstRow);
	    imageContainer.appendChild(secondRow);

	    // Append the container to the mainbody div
	    const mainBody = document.querySelector('.mainbody');
	    if (mainBody) {
	        mainBody.appendChild(imageContainer);
	    }

	}

    if(cityRadio.checked){
        if(searchInput && topnav){
            // Increase z-index to ensure elements are on top
            topnav.style.zIndex = '10';
            searchInput.style.zIndex = '11';
            magnifyingGlass.style.zIndex = '11';
            cityRadio.style.zIndex = '12';
            cityLabel.style.zIndex = '12';
            latitudeRadio.style.zIndex = '12';
            latitudeLabel.style.zIndex = '12';
            logoImg.remove(); 
  
            
            // Optionally, move radio buttons to a more visible position
            if (radioButtons) {
                radioButtons.style.position = 'relative';
                radioButtons.style.zIndex = '12';
            }

            // Your existing positioning logic
            topnav.appendChild(searchInput);
            topnav.appendChild(magnifyingGlass);
            searchInput.style.height = "3rem";
            searchInput.style.marginLeft = '15rem';
            searchInput.style.marginTop = '-2rem';
            searchInput.style.width = '25rem';
            magnifyingGlass.style.height = '2.5rem';
            magnifyingGlass.style.width = '2.5rem';
            magnifyingGlass.style.marginTop = '-2rem';
            magnifyingGlass.style.marginLeft = '-3rem';
            magnifyingGlass.style.filter = "blur(0px)";
            magnifyingGlass.style.marginBottom = '-1.2rem';

            topnav.appendChild(cityRadio);
            topnav.appendChild(cityLabel);
            topnav.appendChild(latitudeRadio);
            topnav.appendChild(latitudeLabel);


        
            cityLabel.style.color = 'white';
            latitudeLabel.style.color = 'white';
            cityRadio.style.marginLeft = '4rem';
            latitudeRadio.style.marginLeft = '4rem';

            topnav.appendChild(button);

            button.style.marginLeft = '60rem';
            button.style.marginTop = '-3rem';
        }
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const magnifyingImage = document.querySelector('.magnifying img');

    // Add event listener to the magnifying image to show the map
    magnifyingImage.addEventListener('click', (event) => {
        event.preventDefault(); // Prevent default anchor link behavior
        map(); // Call the map function
    });
});

function map() {
    // Load map in the 'map' div
    const mapDiv = document.getElementById('map');
    const mapOptions = {
        center: { lat: 34.0549, lng: -118.2426 },  // Los Angeles as center
        zoom: 10,
    };
    console.log("stop 1");

    const map = new google.maps.Map(mapDiv, mapOptions);
    
    // Make map visible
    mapDiv.style.display = 'block';  // Ensure the map is visible
    mapDiv.style.filter = 'none';  // Remove any blur effect
    mapDiv.style.width = '30%';
    mapDiv.style.marginLeft = "32rem";
    
    console.log("stop2");
    // Hide other elements if necessary
    // Optional hiding of weather data div
    
    // Event listener for map clicks to get latitude and longitude
    console.log("stop3");
    google.maps.event.addListener(map, "click", function(event) {
        console.log("got here");
		const lat = event.latLng.lat();  
		const lng = event.latLng.lng(); 
			 
				// Populate field
		document.getElementById('name').value = lat;
		document.getElementById('longitude-input').value = lng;
				
				// Hide map
				
        console.log("stop 4");
        // Optionally hide the map after selection
        mapDiv.style.display = 'none';
        console.log("end of function");
    });
}