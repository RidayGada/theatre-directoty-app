/* ==========================================================
                    CONFIGURATION
========================================================== */

const API_BASE_URL = "http://localhost:8080";


/* ==========================================================
                    GLOBAL VARIABLES
========================================================== */

let authToken = localStorage.getItem("token") || "";

let loggedInUserId = null;

let loggedInRole = null;

let currentTheatreId = null;

let editingReviewId = null;

let cityChart = null;

let ratingChart = null;


/* ==========================================================
                    DOM ELEMENTS
========================================================== */

// Navigation

const loginBtn = document.getElementById("loginBtn");
const signupBtn = document.getElementById("signupBtn");

const exploreBtn = document.getElementById("exploreBtn");
const analyticsBtn = document.getElementById("analyticsBtn");


// Containers

const theatreContainer = document.getElementById("theatreContainer");
const movieContainer = document.getElementById("movieContainer");

const topRatedContainer =
    document.getElementById("topRatedTheatres");

const mostReviewedContainer =
    document.getElementById("mostReviewedTheatres");

const popularMoviesContainer =
    document.getElementById("popularMovies");


// Modals

const loginModal = document.getElementById("loginModal");

const signupModal = document.getElementById("signupModal");

const theatreModal = document.getElementById("theatreModal");

const reviewModal = document.getElementById("reviewModal");

const managerModal = document.getElementById("managerModal");


// Forms

const loginForm = document.getElementById("loginForm");

const signupForm = document.getElementById("signupForm");

const reviewForm = document.getElementById("reviewForm");

const addTheatreForm =
    document.getElementById("addTheatreForm");

const addMovieForm =
    document.getElementById("addMovieForm");

const assignMovieForm =
    document.getElementById("assignMovieForm");


// Misc

const loader = document.getElementById("loader");

const toast = document.getElementById("toast");


/* ==========================================================
                    UTILITY FUNCTIONS
========================================================== */

function showLoader() {

    loader.style.display = "flex";

}


function hideLoader() {

    loader.style.display = "none";

}


function showToast(message) {

    toast.innerText = message;

    toast.classList.add("show");

    setTimeout(() => {

        toast.classList.remove("show");

    }, 3000);

}


function openModal(modal) {

    modal.style.display = "flex";

}


function closeModal(modal) {

    modal.style.display = "none";

}


function closeAllModals() {

    closeModal(loginModal);

    closeModal(signupModal);

    closeModal(theatreModal);

    closeModal(reviewModal);

    closeModal(managerModal);

}


/* ==========================================================
                    JWT FUNCTIONS
========================================================== */

function decodeJWT(token) {

    try {

        const payload = token.split(".")[1];

        return JSON.parse(atob(payload));

    }

    catch (error) {

        return null;

    }

}


function loadLoggedInUser() {

    if (!authToken)
        return;

    const claims = decodeJWT(authToken);

    if (!claims)
        return;

    loggedInUserId = claims.userId;

    loggedInRole = claims.role;

}


/* ==========================================================
                    API HELPER
========================================================== */

async function api(endpoint,
                   method = "GET",
                   body = null,
                   requiresAuth = false) {

    showLoader();

    try {

        const headers = {

            "Content-Type": "application/json"

        };

        if (requiresAuth && authToken) {

            headers["Authorization"] =
                "Bearer " + authToken;

        }

        const response = await fetch(

            API_BASE_URL + endpoint,

            {

                method,

                headers,

                body: body
                    ? JSON.stringify(body)
                    : null

            }

        );

        if (!response.ok) {

            const message = await response.text();

            throw new Error(message);

        }

        if (response.status === 204) {

            return null;

        }

        return await response.json();

    }

    catch (error) {

        showToast(error.message);

        console.error(error);

        return null;

    }

    finally {

        hideLoader();

    }

}


