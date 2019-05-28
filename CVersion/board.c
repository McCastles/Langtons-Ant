#include <stdio.h>
#include "headers/board.h"
#include <string.h>
#include <stdlib.h>

/* Printing the board scheme: OLD FUNCTION, was used during development */
void printBoard(board_t proto)
{
	int i, j;
    for (i = 0; i < proto->w; i++)
        {
			printf("[ ");
            for(j = 0; j < proto->h; j++)
                printf("%d ", proto->board[j][i]);
            printf("]\n");
        }
}


/* Create the new board scheme and fill it with 0 (white) */
board_t createBoard(int rows, int columns)
{
	board_t proto = malloc(sizeof(board_t));
	
	proto->w = columns;
	proto->h = rows;
	proto->board = malloc (columns * sizeof(int*));

	int i, j;
	for (i = 0; i < columns; i++)
	{
		proto->board[i] = malloc(rows*sizeof(int));
		for (j = 0; j < rows; j++)
			proto->board[i][j] = 0;
	};
	
	return proto;
}




