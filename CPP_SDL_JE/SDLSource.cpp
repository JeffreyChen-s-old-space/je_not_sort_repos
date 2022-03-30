//FLASHING SCREEN CODE
//Demonstrates screen freeze when you minimise then maximise the screen....

#include "SDL.h"

using namespace std;

int main(int argc, char* args[])
{
    //Initialise SDL2, declare Window + Renderer
    SDL_Init(SDL_INIT_VIDEO | SDL_INIT_AUDIO); //Initialize SDL for video and audio
    SDL_Window* pWindow = NULL;
    pWindow = SDL_CreateWindow("Minimise Maximise Test", 50, 50, 600, 600, SDL_WINDOW_RESIZABLE | SDL_WINDOW_MAXIMIZED);

    //SDL event for detecting X-out (quit)
    SDL_Event event;
    bool quit = false;

    //Loop
    while (quit == false)   //While SDL has not been X'ed out
    {
        //Handle quit
        if (SDL_PollEvent(&event) != 0)
        {
            if (event.type == SDL_QUIT) quit = true; //Press X-out to quit (but not visible on fullscreen)
        }

    }

    SDL_DestroyWindow(pWindow);
    SDL_Quit();

    return 0;
}