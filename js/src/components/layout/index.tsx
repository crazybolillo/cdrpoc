"use client";

import { Header } from "@components/header";
import { ThemedLayoutV2 } from "@refinedev/antd";
import React from "react";
import { Typography } from "antd";

export const ThemedLayout = ({ children }: React.PropsWithChildren) => {
  return (
    <ThemedLayoutV2
      Title={() => <Typography>AskCDR</Typography>}
      Header={() => <Header sticky />}
    >
      {children}
    </ThemedLayoutV2>
  );
};
