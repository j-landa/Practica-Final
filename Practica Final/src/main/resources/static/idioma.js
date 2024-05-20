function changeLanguage(language) {
   fetch(`Traducciones/${language}.json`)
     .then(res => res.json())
     .then(translations => {
       document.getElementById('title').innerText = translations.title;
       document.getElementById('header-title').innerText = translations.title;
 
       document.getElementById('home').innerText = translations.home;
       document.getElementById('fish').innerText = translations.fish;
       document.getElementById('tanks').innerText = translations.tanks;
       document.getElementById('form').innerText = translations.form;
 
       localStorage.setItem('selectedLanguage', language);
     });
 }
 

 const selectedLanguage = localStorage.getItem('selectedLanguage') || 'es';

 document.getElementById('languageSelector').value = selectedLanguage;
 

 changeLanguage(selectedLanguage);