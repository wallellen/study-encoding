#include <stdio.h>

int main(int argc, char* argv[]) {
    int i, j;
    FILE* pf = fopen("GBK_table.txt", "wb");
    if (pf == NULL) {
        perror("Error cased, can not open file\n");
        return 1;
    }

    for (i = 1; i <= 119; i++) {
        if (i > 1) {
            fputs("\n\n", pf);
        }
        fprintf(pf, "--------------------------------------------%d------------------------------------------------\n", i);
        for (j = 1; j <= 94; j++) {
            char s[] = {(char)(0x80 + i), (char)(0xA0 + j), 0};
            if (j % 50 == 0) {
                fputs("\n", pf);
            }
            fputs(s, pf);
        }
    }
    printf("Work finish\n");
    return 0;
}