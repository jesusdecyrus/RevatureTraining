/**
 * Clear all child elements of allPokemon container
 */
function clearAllPokemon() {
  const allPokemon = document.getElementById('allPokemon');
  allPokemon.innerHTML = '';
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
    console.error('Unable to view all Pokemon: ', error);
  });
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
    buttonDiv.innerHTML = `<button class="catchButton">Catch</button>
                           <button class="runButton" onclick="randomEncounter()">Run</button>`;
     document.getElementById('allPokemon').appendChild(buttonDiv);
  })
  .catch(error => {
    console.error('Unable to random encounter: ', error);
  });
}

setTimeout(() => {
  pokemonDiv.classList.add('show');
}, 10);