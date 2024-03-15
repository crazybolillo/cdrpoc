import { useNotificationProvider } from "@refinedev/antd";
import { Refine } from "@refinedev/core";
import routerProvider from "@refinedev/nextjs-router";
import { Metadata } from "next";
import { cookies } from "next/headers";
import React, { Suspense } from "react";

import { ColorModeContextProvider } from "@contexts/color-mode";
import { dataProvider } from "@providers/data-provider";
import { AntdRegistry } from "@ant-design/nextjs-registry";
import "@refinedev/antd/dist/reset.css";
import { ThemedLayout } from "@components/layout";

export const metadata: Metadata = {
  title: "Asterisk CDR",
  description: "Browse through Asterisk CDR.",
  icons: {
    icon: "/favicon.ico",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const cookieStore = cookies();
  const theme = cookieStore.get("theme");

  return (
    <html lang="en">
      <body>
        <Suspense>
          <AntdRegistry>
            <ColorModeContextProvider defaultMode={theme?.value}>
              <Refine
                routerProvider={routerProvider}
                dataProvider={dataProvider}
                notificationProvider={useNotificationProvider}
                resources={[
                  {
                    name: "Call",
                    list: "/calls",
                    show: "/calls/:id",
                    meta: {
                      canDelete: false,
                    },
                  },
                ]}
                options={{
                  syncWithLocation: true,
                  useNewQueryKeys: true,
                }}
              >
                <ThemedLayout>{children}</ThemedLayout>
              </Refine>
            </ColorModeContextProvider>
          </AntdRegistry>
        </Suspense>
      </body>
    </html>
  );
}
