// Global Current Random Encounter
var currentEncounterPokemon;

/**
 * Clear all child elements of allPokemon container
 */
function clearAllPokemon() {
  const allPokemon = document.getElementById('allPokemon');
  allPokemon.innerHTML = '';
}

/**
 * Random encounter button
 */
function randomEncounter() {
  // Clear allPokemon container
  clearAllPokemon();
  
  // Communicate with Java
  fetch('/randomPokemon', {
    method: 'GET'
  })
  .then(response => response.json())
  .then(data => {
    // Set currentEncounterPokemon
    currentEncounterPokemon = data;
    
    // Create and append the div for the random pokemon
    const pokemonDiv = document.createElement('div');
    pokemonDiv.className = 'onePokemon';
    pokemonDiv.innerHTML = `<a>${data.name}</a>
                            <p><strong>Level:</strong> ${data.level}</p>
                            <p><strong>Type:</strong> ${data.type}</p>
                            <p><strong>Pokedex Number:</strong> ${data.pokemonID}</p>
                            <p><strong>Gender:</strong> ${data.gender}</p>
                            <p><strong>Shiny:</strong> ${data.shiny ? 'Yes' : 'No'}</p>`;
    document.getElementById('allPokemon').appendChild(pokemonDiv);

    // Append button div
    const buttonDiv = document.createElement('div');
    buttonDiv.className = 'reButtons';
    buttonDiv.innerHTML = `<button class="catchButton" onclick="catchPokemon()">Catch</button>
                           <button class="runButton" onclick="randomEncounter()">Run</button>`;
     document.getElementById('allPokemon').appendChild(buttonDiv);
  })
  .catch(error => {
    console.error('Unable to Random Encounter: ', error);
  });
}

/**
 * Catch pokemon button
 */
function catchPokemon() {
  // Clear allPokemon container
  clearAllPokemon();

  // HARD CODED CHANGE LATER
  currentEncounterPokemon.trainerID = 4;

  // Communicate with Java
  fetch('/pokemon/add', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(currentEncounterPokemon) 
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Error with network: ' + response.statusText);
    }
      return response.json();
  })
  .then(data => {
      console.log('Successfully Added Pokemon!');
  })
  .catch(error => {
      console.error('Error Adding Pokemon: ', error);
  });
}

/**
 * View owned pokemon button
 */
function viewOwnedPokemon() {
  // Clear allPokemon container
  clearAllPokemon();

  // HARD CODED CHANGE LATER
  var trainerID = 1;
  
  // Communicate with Java
  fetch('/pokemon/trainer/' + trainerID, {
    method: 'GET'
  })
  .then(response => response.json())
  .then(data => {
    // Iterate through each array
    data.forEach(pokemon => {
      const pokemonDiv = document.createElement('div');
      pokemonDiv.className = 'onePokemon';

      // Create and set innerHTML for one pokemon
      pokemonDiv.innerHTML = `<a>${pokemon.name}</a>
        <p><strong>Level:</strong> ${pokemon.level}</p>
        <p><strong>Type:</strong> ${pokemon.type}</p>
        <p><strong>Pokemon ID:</strong> ${pokemon.pokemonID}</p>
        <p><strong>Trainer ID:</strong> ${pokemon.trainerID}</p>
        <p><strong>Gender:</strong> ${pokemon.gender}</p>
        <p><strong>Shiny:</strong> ${pokemon.shiny ? 'Yes' : 'No'}</p>`;

      // Append the new div to the allPokemon container
      document.getElementById('allPokemon').appendChild(pokemonDiv);
    })
  })
  .catch(error => {
    console.error('Unable To View All Pokemon: ', error);
  });
}

/**
 * View all pokemon button
 */
function viewAllPokemon() {
  // Clear allPokemon container
  clearAllPokemon();
  
  // Communicate with Java
  fetch('/pokemon', {
    method: 'GET'
  })
  .then(response => response.json())
  .then(data => {
    // Iterate through each array
    data.forEach(pokemon => {
      const pokemonDiv = document.createElement('div');
      pokemonDiv.className = 'onePokemon';

      // Create and set innerHTML for one pokemon
      pokemonDiv.innerHTML = `<a>${pokemon.name}</a>
        <p><strong>Level:</strong> ${pokemon.level}</p>
        <p><strong>Type:</strong> ${pokemon.type}</p>
        <p><strong>Pokemon ID:</strong> ${pokemon.pokemonID}</p>
        <p><strong>Trainer ID:</strong> ${pokemon.trainerID}</p>
        <p><strong>Gender:</strong> ${pokemon.gender}</p>
        <p><strong>Shiny:</strong> ${pokemon.shiny ? 'Yes' : 'No'}</p>`;

      // Append the new div to the allPokemon container
      document.getElementById('allPokemon').appendChild(pokemonDiv);
    })
  })
  .catch(error => {
    console.error('Unable To View All Pokemon: ', error);
  });
}