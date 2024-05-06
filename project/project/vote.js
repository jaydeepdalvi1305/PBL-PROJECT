let votes = {
    AAP: 0,
    BJP:0,
    CONGRESS:0
};

function vote(party) {
    if (!localStorage.getItem('voted')) {
        votes[party]++;
        localStorage.setItem('voted', 'true');
        displayResults();
    } else {
        alert('You have already voted!');
    }
}

function displayResults() {
    document.getElementById('party1Votes').textContent = votes.AAP;
    document.getElementById('party2Votes').textContent = votes.BJP;
    document.getElementById('party3Votes').textContent = votes.CONGRESS;
}