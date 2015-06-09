#include <stdio.h>

int main(int argc, char* argv[]) {
    int i;
    FILE* pf = fopen("ISO8859.txt", "wb");
    if (pf == NULL) {
        perror("Error caused, can not open file\n");
        return 1;
    }

    for (i = 1; i < 0xFF; i++) {
        char c[] = {i, '\0'};
        if (i % 50 == 0) {
            fputs("\n", pf);
        }
        fputs(c, pf);
    }
    printf("Work finish\n");
    return 0;
}